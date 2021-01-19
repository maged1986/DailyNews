package com.megednan.dailynews.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megednan.dailynews.db.NewsDao
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleViewModel @ViewModelInject constructor(
        private val newsRepository: NewsRepository
) : ViewModel() {
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }


}