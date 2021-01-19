package com.megednan.dailynews.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.megednan.dailynews.dataSource.BreakingNewsDataSource
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.network.NewsAPI
import javax.inject.Inject

class BreakingNewsRepository @Inject constructor(
        private val newsAPI: NewsAPI
) {
    fun getBreakingNews():LiveData<PagingData<Article>>{
     return   Pager(
            config = PagingConfig(
                    pageSize = 20,
                    maxSize = 100,
                    enablePlaceholders = false
            ),
            pagingSourceFactory = { BreakingNewsDataSource(newsAPI) }
    ).liveData}
}