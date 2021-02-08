package br.com.newsapp.data.repository

import androidx.annotation.WorkerThread
import br.com.newsapp.data.model.DocumentResponse
import br.com.newsapp.data.source.remote.Resource
import br.com.newsapp.data.source.remote.service.ServiceGenerator
import br.com.newsapp.data.source.remote.service.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DetailsRepository @Inject constructor(private var serviceGenerator: ServiceGenerator) {

    @WorkerThread
    suspend fun getDocuments(id: String): Resource<List<DocumentResponse>?> = withContext(Dispatchers.IO) {
        // não criar o service toda hora
        val callService = serviceGenerator.createService(APIService::class.java, true).getNewsDetails(id)
        return@withContext serviceGenerator.runAsyncDeferredAwaited(callService)
    }

}