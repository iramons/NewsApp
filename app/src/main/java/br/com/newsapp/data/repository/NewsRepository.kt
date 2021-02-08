package br.com.newsapp.data.repository

import androidx.annotation.WorkerThread
import br.com.newsapp.data.model.News
import br.com.newsapp.data.source.remote.Resource
import br.com.newsapp.data.source.remote.service.ServiceGenerator
import br.com.newsapp.data.source.remote.service.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private var serviceGenerator: ServiceGenerator) {

    @WorkerThread
    suspend fun getNews(): Resource<List<News>?> = withContext(Dispatchers.IO) {
        // não criar o service toda hora
        val callService = serviceGenerator.createService(APIService::class.java, true).getNews()
        return@withContext serviceGenerator.runAsyncDeferredAwaited(callService)
    }

}