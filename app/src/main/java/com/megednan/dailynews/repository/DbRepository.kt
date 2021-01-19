package com.megednan.dailynews.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.megednan.dailynews.dataSource.BreakingNewsDataSource
import com.megednan.dailynews.dataSource.DBNewsDataSource
import com.megednan.dailynews.dataSource.NewsDataSource
import com.megednan.dailynews.db.NewsDao
import com.megednan.dailynews.db.NewsDb
import com.megednan.dailynews.models.Article
import javax.inject.Inject

class DbRepository @Inject constructor(
        private val newsDb: NewsDb
) {
      fun getBreakingNews():LiveData<PagingData<Article>>{
        return   Pager(
                config = PagingConfig(
                        pageSize = 20,
                        maxSize = 100,
                        enablePlaceholders = false
                ),
                pagingSourceFactory = { DBNewsDataSource(newsDb) }
        ).liveData}
}
