package br.com.newsapp.data.source.remote

import android.annotation.SuppressLint
import br.com.newsapp.BuildConfig
import br.com.newsapp.data.source.remote.service.ServiceGenerator
import br.com.newsapp.util.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

internal class AuthInterceptor(private val hasAuth: Boolean = true) : Interceptor {

    val loggerInterceptor: HttpLoggingInterceptor
        @SuppressLint("TimberArgCount")
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                Timber.i("HttpIntercept", loggingInterceptor.level.name)

                loggingInterceptor.apply {
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                }.level = HttpLoggingInterceptor.Level.BODY
            }
            return loggingInterceptor
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestChain = chain.request()
        val requestBuilder = requestChain.newBuilder()
        val token = SessionManager.fetchAuthToken()

        if (hasAuth)
            requestBuilder.addHeader(ServiceGenerator.AUTH_TYPE, "${ServiceGenerator.AUTH_TYPE_VALUE} $token")

        requestBuilder.addHeader(ServiceGenerator.CONTENT_TYPE, ServiceGenerator.CONTENT_TYPE_VALUE)
        requestBuilder.method(requestChain.method, requestChain.body)
        return chain.proceed(requestBuilder.build())
    }

}