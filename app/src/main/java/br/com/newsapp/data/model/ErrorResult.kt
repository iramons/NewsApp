package br.com.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResult (
    @SerializedName("erro") var error: String? = null
): JsonModel() {
    fun message(): String? = error
}
