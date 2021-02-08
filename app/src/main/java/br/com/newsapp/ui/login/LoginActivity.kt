package br.com.newsapp.ui.login

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import br.com.ilhasoft.support.validation.Validator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.newsapp.R
import br.com.newsapp.commom.base.BaseActivity
import br.com.newsapp.commom.extensions.ContextExtensions.hideKeyboard
import br.com.newsapp.commom.extensions.ContextExtensions.showError
import br.com.newsapp.commom.extensions.postDelayed
import br.com.newsapp.data.source.remote.Status
import br.com.newsapp.databinding.ActivityLoginBinding
import br.com.newsapp.di.CustomViewModelFactory
import br.com.newsapp.ui.news.NewsActivity
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override val layoutId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        validator()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initUI()
        initObservers()
    }

    override fun initUI() {

    }

    override fun initObservers() {
        viewModel.resourceStatus.observe(this, Observer { resourceResult ->

            when (resourceResult.status) {
                Status.LOADING -> {
                    binding.loginButton.startMorphAnimation()
                }
                Status.EMPTY,
                Status.ERROR -> {
                    setButtonChanges(resourceResult.hasError)
                    this.showError(resourceResult)
                }
                Status.SUCCESS -> {
                    if (resourceResult.code == 200) {
                        startActivity(Intent(this@LoginActivity, NewsActivity::class.java))
                    }
                }
                else -> {
                }
            }
            revertButtonAnimationDelayed()
        })
    }

    private fun validator() {
        val validator = Validator(binding)
        binding.loginButton.setOnClickListener {
            hideKeyboard(this)
            if (validator.validate()) {
                viewModel.onCallLogin()
            }
        }
    }

    private fun setButtonChanges(error: Boolean) {
        if (error) {
            val errorIcon = BitmapFactory.decodeResource(resources,
                R.drawable.round_priority_high_white_18)
            binding.loginButton.doneLoadingAnimation(
                ContextCompat.getColor(
                    this,
                    R.color.colorRedDark),
                errorIcon)
            revertButtonAnimationDelayed()
        } else {
            val checkIcon = BitmapFactory.decodeResource(resources, R.drawable.round_check_24)
            binding.loginButton.doneLoadingAnimation(
                ContextCompat.getColor(
                    this,
                    R.color.colorGreenLight
                ), checkIcon
            )
        }
    }

    private fun revertButtonAnimationDelayed() {
        postDelayed(2000, ::revertButtonAnimation)
    }

    private fun revertButtonAnimation() {
        binding.loginButton.revertAnimation()
        binding.loginButton.setBackgroundResource(R.drawable.button_rounded)
    }

}
