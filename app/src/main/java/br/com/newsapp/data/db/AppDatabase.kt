package br.com.newsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.newsapp.data.dao.NewsDAO
import br.com.newsapp.data.model.Document
import br.com.newsapp.data.model.News
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(
        entities = [
            News::class,
            Document::class
                   ],
        exportSchema = false,
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDAO(): NewsDAO

    object Props {
        const val DB_NAME: String = "newsapp-room.db"
    }

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        private const val NUMBER_OF_THREADS = 4
        private val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        @Synchronized
        operator fun invoke(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class.java) { buildDatabase(context) }
        }

        @Synchronized
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                Props.DB_NAME
            )
                .fallbackToDestructiveMigration()
                .setTransactionExecutor(databaseWriteExecutor)
                //.addCallback(DB_CALLBACK)
                .build()
                .also { INSTANCE = it }
        }

        private val DB_CALLBACK = object : RoomDatabase.Callback() {
        }
    }
}