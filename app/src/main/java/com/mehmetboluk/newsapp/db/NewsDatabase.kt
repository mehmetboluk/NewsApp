package com.mehmetboluk.newsapp.db

import android.content.Context
import androidx.room.*
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.SourceTypeConverter

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao() : NewsDao

    companion object{

        @Volatile
        private var instance : NewsDatabase ?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) = Room.
        databaseBuilder(context.applicationContext, NewsDatabase::class.java,"newsdatabase").allowMainThreadQueries().build()
    }

}