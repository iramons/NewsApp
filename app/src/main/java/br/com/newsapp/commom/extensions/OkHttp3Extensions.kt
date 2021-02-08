package br.com.newsapp.commom.extensions

import br.com.newsapp.data.model.ErrorResult
import br.com.newsapp.data.source.remote.service.ServiceGenerator
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response

object Okhttp3Extensions {

    fun String.stringToRequestBody(): RequestBody {
        return this.toRequestBody(ServiceGenerator.CONTENT_TYPE_VALUE.toMediaTypeOrNull())
    }

    inline fun <reified T> T.objectToRequestBody(): RequestBody {
        return this.toJsonString().stringToRequestBody()
    }

    fun <T> Response<T>?.toErrorResult(): ErrorResult? {
        if (this == null)
            return this

        return this.errorBody()?.let {
            it.toErrorResult()
        }
    }

    fun ResponseBody?.toErrorResult(): ErrorResult? {
        if (this == null)
            return this

        return Gson().fromJson(this.charStream(), ErrorResult::class.java)
    }
}