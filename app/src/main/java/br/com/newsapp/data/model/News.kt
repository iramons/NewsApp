package br.com.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.newsapp.data.db.Databases
import com.google.gson.annotations.SerializedName

@Entity(tableName = Databases.News.TABLE_NAME)
data class News(
    @PrimaryKey
    @SerializedName("id_documento") val idDoc: String,
    @SerializedName("chapeu") val hat: String? = null,
    @SerializedName("titulo") val title: String? = null,
    @SerializedName("linha_fina") val thinLine: String? = null,
    @SerializedName("data_hora_publicacao") val dateTimePublication: String? = null,
    @SerializedName("credito") val credit: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("imagem") val image: String? = null,
    @SerializedName("imagem_credito") val imageCredit: String? = null,
    @SerializedName("source") val source: String? = null,
): JsonModel()
