package br.com.newsapp.data.dao

import androidx.annotation.Size
import androidx.room.*
import br.com.newsapp.data.db.Databases
import br.com.newsapp.data.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDAO {

    val db
    get() = Databases.News


    @Query("SELECT * FROM ${db.TABLE_NAME}")
    fun read(): Flow<News>

    @Query("SELECT * FROM ${db.TABLE_NAME}")
    fun get(): News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(@Size(min = 1) vararg model: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(@Size(min = 1) list: List<News>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(@Size(min = 1) model: News)

    @Delete
    suspend fun delete(@Size(min = 1) model: News)
}