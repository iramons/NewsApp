package br.com.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class DocumentResponse(
        @SerializedName("documento") val document: Document? = null,
): JsonModel()

