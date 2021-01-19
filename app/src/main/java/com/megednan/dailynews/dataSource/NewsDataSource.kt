package com.megednan.dailynews.dataSource

import androidx.paging.PagingSource
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.network.NewsAPI
import com.megednan.dailynews.util.Constants.Companion.API_KEY
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val NEWS_PAGE_INDEX=1
class NewsDataSource @Inject constructor(
        private val query: String,
        private val newsAPI: NewsAPI
) :PagingSource<Int,Article>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: NEWS_PAGE_INDEX

        return try {
            val response = newsAPI.searchForNews(query, position,API_KEY)
            val news = response.body()!!.articles

            LoadResult.Page(
                    data = news,
                    prevKey = if (position == NEWS_PAGE_INDEX) null else position - 1,
                    nextKey = if (news.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}