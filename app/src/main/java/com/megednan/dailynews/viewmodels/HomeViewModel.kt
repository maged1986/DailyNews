package com.megednan.dailynews.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.repository.BreakingNewsRepository

class HomeViewModel @ViewModelInject constructor(
        private val repository: BreakingNewsRepository,
): ViewModel(){

    fun getNews():LiveData<PagingData<Article>> {
      return  repository.getBreakingNews().cachedIn(viewModelScope)
    }
}