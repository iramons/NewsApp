package br.com.newsapp.data.source.remote.service

import androidx.annotation.WorkerThread
import br.com.newsapp.data.model.APITokenResult
import br.com.newsapp.data.model.News
import br.com.newsapp.data.model.DocumentResponse
import br.com.newsapp.util.APIPaths
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @WorkerThread
    @POST(APIPaths.login)
    fun login(@Body request: RequestBody): Call<APITokenResult>

    @WorkerThread
    @GET(APIPaths.news)
    fun getNews(): Call<List<News>>

    @WorkerThread
    @GET(APIPaths.news_details)
    fun getNewsDetails(@Path("id") id: String): Call<List<DocumentResponse>>

}