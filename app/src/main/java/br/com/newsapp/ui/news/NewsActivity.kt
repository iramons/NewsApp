package br.com.newsapp.ui.news

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import br.com.newsapp.R
import br.com.newsapp.commom.base.BaseActivity
import br.com.newsapp.commom.extensions.ActivityExtensions.setupToolbar
import br.com.newsapp.commom.extensions.ContextExtensions.showError
import br.com.newsapp.data.model.News
import br.com.newsapp.data.source.remote.Status
import br.com.newsapp.databinding.ActivityNewsBinding
import br.com.newsapp.di.CustomViewModelFactory
import br.com.newsapp.ui.details.DetailsActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter

    override val layoutId: Int
        get() = R.layout.activity_news

    private val rootView: View
        get() = binding.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initUI()
    }

    override fun initUI() {
        lifecycleScope.launch { createRecyclerViewAndAdapter() }
        initObservers()
    }

    private fun createRecyclerViewAndAdapter() {
        newsAdapter = NewsAdapter(emptyList(), onItemClick = {
            val intent = DetailsActivity.getStartIntent(this, it.idDoc)
            startActivity(intent)
        })
        recyclerView = rootView.findViewById(R.id.recycler_view_app) as RecyclerView
        recyclerView.apply {
            this.adapter = newsAdapter
            (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun initObservers() {
        lifecycle.addObserver(viewModel)

        viewModel.listNews.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    onLoading(true)
                }
                Status.EMPTY,
                Status.ERROR -> {
                    onLoading(false)
                    rootView.context.showError(it)
                }
                Status.SUCCESS -> {
                    if (it.data != null) updateData(it.data)
                    onLoading(false)
                }
                else -> {}
            }
        })

        fetchNews()
    }

    private fun fetchNews() {
        viewModel.fetchNewsList()
    }

    private fun updateData(list: List<News>) {
        newsAdapter.list = list
        newsAdapter.notifyDataSetChanged()
    }
}