package com.megednan.dailynews.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import com.megednan.dailynews.R
import com.megednan.dailynews.adapters.NewsAdapter
import com.megednan.dailynews.adapters.NewsLoadStateAdapter
import com.megednan.dailynews.databinding.BusinessFragmentBinding
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.viewmodels.BusinessViewModel
import com.megednan.dailynews.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessFragment : Fragment(R.layout.business_fragment) {
    private val viewModel by viewModels<BusinessViewModel>()
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=BusinessFragmentBinding.bind(view)
        val linearLayoutManager= LinearLayoutManager(context)
        newsAdapter= NewsAdapter()
        viewModel.getNews().observe(viewLifecycleOwner, Observer {pagedList->
            newsAdapter.submitData(lifecycle,pagedList)
        })
        binding.businessFragRv.apply {
            setHasFixedSize(true)
            layoutManager=linearLayoutManager
            itemAnimator = null
            adapter = newsAdapter.withLoadStateHeaderAndFooter(
                    header = NewsLoadStateAdapter { newsAdapter.retry()}
                    ,footer= NewsLoadStateAdapter{newsAdapter.retry()})
        }
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                    R.id.action_businessFragment_to_articleFragment,
                    bundle
            )

        }




    }


}