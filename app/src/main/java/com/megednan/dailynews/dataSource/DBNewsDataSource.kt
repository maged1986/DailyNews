package com.megednan.dailynews.dataSource

import androidx.paging.PagingSource
import com.megednan.dailynews.db.NewsDao
import com.megednan.dailynews.db.NewsDb
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.util.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


private const val NEWS_PAGE_INDEX=1
class DBNewsDataSource @Inject constructor(
        private val newsDb: NewsDb
): PagingSource<Int, Article>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: NEWS_PAGE_INDEX

        return try {
            val response = newsDb.getArticleDao()
            val articleList= response.getAllArticles()
            LoadResult.Page(
                    data = articleList,
                    prevKey = if (position == NEWS_PAGE_INDEX) null else position - 1,
                    nextKey = if (articleList.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}