package br.com.newsapp.data.repository

import br.com.newsapp.commom.extensions.Okhttp3Extensions.objectToRequestBody
import br.com.newsapp.data.model.APITokenResult
import br.com.newsapp.data.model.Login
import br.com.newsapp.data.source.remote.Resource
import br.com.newsapp.data.source.remote.service.ServiceGenerator
import br.com.newsapp.data.source.remote.service.APIService
import br.com.newsapp.util.SessionManager
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private var serviceGenerator: ServiceGenerator
) {

    suspend fun login(user: String, pass: String): Resource<APITokenResult> {
        val body = Login(user = user, pass = pass)
        val requestBody: RequestBody = body.objectToRequestBody()
        val callService = serviceGenerator.createService(APIService::class.java, false).login(requestBody)

        return serviceGenerator.runAsync(callService, onResponse = {
            it.data?.let { apiTokenResult ->
                SessionManager.onLogin(user, pass, apiTokenResult)
            }
            return@runAsync
        })
    }

}