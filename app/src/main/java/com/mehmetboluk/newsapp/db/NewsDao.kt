package com.mehmetboluk.newsapp.db

import androidx.room.*
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getAll() : MutableList<Article>

}