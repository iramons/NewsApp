package br.com.newsapp.data.model

import androidx.room.Entity
import br.com.newsapp.data.db.Databases
import com.google.gson.annotations.SerializedName

@Entity(tableName = Databases.Login.TABLE_NAME)
data class Login(
    @SerializedName("user") val user: String? = null,
    @SerializedName("pass") val pass: String? = null,
): JsonModel()
