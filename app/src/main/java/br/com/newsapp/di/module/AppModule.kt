package br.com.newsapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import br.com.newsapp.BaseApp
import br.com.newsapp.data.db.AppDatabase
import br.com.newsapp.data.source.remote.service.ServiceGenerator
import br.com.newsapp.di.CustomViewModelFactory
import br.com.newsapp.di.annotation.AppBaseApplication
import br.com.newsapp.util.Preferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @AppBaseApplication
    fun provideApplication(app : BaseApp): BaseApp = app

    @Provides
    @Singleton
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.applicationContext.getSharedPreferences(Preferences.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return AppDatabase.invoke(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideCustomViewModelFactory(customCustomViewModelFactory: CustomViewModelFactory): ViewModelProvider.Factory {
        return customCustomViewModelFactory
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideServiceGenerator(): ServiceGenerator {
        return ServiceGenerator()
    }
}