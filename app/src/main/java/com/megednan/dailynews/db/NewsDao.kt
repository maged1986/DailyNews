package com.megednan.dailynews.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.megednan.dailynews.models.Article

@Dao
 interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
      fun getAllArticles(): List<Article>
   @Delete
    suspend fun deleteArticle(article: Article)
}