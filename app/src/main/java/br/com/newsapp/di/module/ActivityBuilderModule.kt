package br.com.newsapp.di.module

import br.com.newsapp.di.annotation.ActivityScoped
import br.com.newsapp.ui.details.DetailsActivity
import br.com.newsapp.ui.login.LoginActivity
import br.com.newsapp.ui.news.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributesNewsActivity(): NewsActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributesDetailsActivity(): DetailsActivity

}
