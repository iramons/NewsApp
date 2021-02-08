package br.com.newsapp.di.module

import androidx.lifecycle.ViewModel
import br.com.newsapp.di.annotation.AppViewModel
import br.com.newsapp.ui.details.DetailsViewModel
import br.com.newsapp.ui.login.LoginViewModel
import br.com.newsapp.ui.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @AppViewModel(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @AppViewModel(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @AppViewModel(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

}