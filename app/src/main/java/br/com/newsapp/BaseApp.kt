package br.com.newsapp

import android.content.Context
import br.com.newsapp.di.component.DaggerAppComponent
import br.com.newsapp.util.AppPreferences
import br.com.newsapp.util.SessionManager
import br.com.newsapp.util.StrictModeManager
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class BaseApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        lateinit var app: BaseApp
        lateinit var appContext: Context

        var glideOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(false)

        var glideOptionsSkipMemory = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .skipMemoryCache(true)
    }

    override fun onCreate() {
        // Enable strict mode before Dagger
        if (BuildConfig.DEBUG) {
            StrictModeManager.enableStrictMode()
            StrictModeManager.allowDiskReads{
                super.onCreate()
            }
        } else {
            super.onCreate()
        }
        app = this
        appContext = applicationContext

        runBlocking {
            AppPreferences.init(applicationContext)
            SessionManager.init(applicationContext)

            if (BuildConfig.DEBUG) {
                /** Stetho: For initialize put in Chrome -> chrome://inspect/#devices **/
                Stetho.initializeWithDefaults(applicationContext)
                Timber.plant(Timber.DebugTree())
            }
            GlideBuilder().setDefaultRequestOptions(glideOptions)
        }
    }
}

/** extension function to provide TAG value for LOGs **/
val Any.TAG: String
    get() {
        return if (!javaClass.isAnonymousClass) {
            val name = javaClass.simpleName
            if (name.length <= 23) name else name.substring(0, 23)
        } else {
            val name = javaClass.name
            if (name.length <= 23) name else name.substring(name.length - 23, name.length)
        }
    }