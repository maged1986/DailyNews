package com.megednan.dailynews.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.megednan.dailynews.db.NewsDao
import com.megednan.dailynews.db.NewsDb
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.repository.DbRepository

class SavedViewModel @ViewModelInject constructor(
         val repository: DbRepository
) : ViewModel() {
     fun getNews(): LiveData<PagingData<Article>> {
        return  repository.getBreakingNews().cachedIn(viewModelScope)
    }
}