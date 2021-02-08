package br.com.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.newsapp.data.db.Databases
import com.google.gson.annotations.SerializedName

@Entity(tableName = Databases.Document.TABLE_NAME)
data class Document (

    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("produto") val product: String? = null,
    @SerializedName("editoria") val editorial: String? = null,
    @SerializedName("subeditoria") val subEditorial: String? = null,
    @SerializedName("titulo") val title: String? = null,
    @SerializedName("credito") val credit: String? = null,
    @SerializedName("datapub") val datePub: String? = null,
    @SerializedName("horapub") val timePub: String? = null,
    @SerializedName("linhafina") val thinLine: String? = null,
    @SerializedName("imagem") val image: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("creditoImagem") val creditImage: String? = null,
    @SerializedName("legendaImagem") val legendImage: String? = null,
    @SerializedName("origem") val origin: String? = null,
    @SerializedName("corpoformatado") val formattedBody: String? = null
): JsonModel()