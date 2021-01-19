package com.megednan.dailynews.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.megednan.dailynews.dataSource.NewsDataSource
import com.megednan.dailynews.db.NewsDao
import com.megednan.dailynews.db.NewsDb
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.network.NewsAPI
import javax.inject.Inject

class NewsRepository @Inject constructor(
        private val newsAPI: NewsAPI,
        val newsDb: NewsDb
) {
    fun getSearchResults(query: String) =
            Pager(
                    config = PagingConfig(
                            pageSize = 20,
                            maxSize = 100,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = {NewsDataSource(query,newsAPI) }
            ).liveData
    suspend fun upsert(article: Article) =newsDb.getArticleDao().upsert(article)

}