package br.com.newsapp.ui.details

import android.content.Context
import android.content.Intent
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
import br.com.newsapp.data.model.Document
import br.com.newsapp.data.source.remote.Status
import br.com.newsapp.databinding.ActivityDetailsBinding
import br.com.newsapp.di.CustomViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: DetailsAdapter

    private var docsList = ArrayList<Document>()

    override val layoutId: Int
        get() = R.layout.activity_details

    private val rootView: View
        get() = binding.root

    companion object {
        var idDoc : String? = null
        fun getStartIntent(context: Context, id: String): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                Timber.d("id($idDoc)")
                idDoc = id
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
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
        mAdapter = DetailsAdapter(emptyList(), onItemClick = {})
        recyclerView = rootView.findViewById(R.id.recycler_view_app) as RecyclerView
        recyclerView.apply {
            this.adapter = mAdapter
            (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun initObservers() {
        lifecycle.addObserver(viewModel)

        viewModel.newsList.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> onLoading(true)

                Status.EMPTY, Status.ERROR -> {
                    onLoading(false)
                    rootView.context.showError(it)
                }
                Status.SUCCESS -> {
                    if (it.data != null) {
                        it.data.forEach { newsDetails ->
                            newsDetails.document?.let { doc -> addToList(doc) }
                        }
                        updateData()
                    }
                    onLoading(false)
                }
                else -> {}
            }
        })

        idDoc?.let { fetchData(it) }
    }

    private fun fetchData(id: String) {
        viewModel.fetchNewsDetails(id)
    }

    private fun addToList(item: Document) {
        docsList.add(item)
    }

    private fun updateData() {
        mAdapter.list = docsList
        docsList[0].editorial?.let { setupToolbar(title = it, showBackButton = true) }
        mAdapter.notifyDataSetChanged()
    }
}