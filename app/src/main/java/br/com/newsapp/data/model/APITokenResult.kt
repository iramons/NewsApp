package br.com.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class APITokenResult (

    @SerializedName("token")
    val token: String
)