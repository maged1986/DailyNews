package com.megednan.dailynews.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.repository.NewsRepository

class CultureViewModel @ViewModelInject constructor(
        private val repository: NewsRepository
) : ViewModel() {
    fun getNews(query:String): LiveData<PagingData<Article>> {
        return  repository.getSearchResults(query).cachedIn(viewModelScope)
    }
}