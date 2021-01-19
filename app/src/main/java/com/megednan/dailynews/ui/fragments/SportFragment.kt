package com.megednan.dailynews.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.megednan.dailynews.R
import com.megednan.dailynews.adapters.NewsAdapter
import com.megednan.dailynews.adapters.NewsLoadStateAdapter
import com.megednan.dailynews.databinding.BusinessFragmentBinding
import com.megednan.dailynews.databinding.SportFragmentBinding
import com.megednan.dailynews.viewmodels.BusinessViewModel
import com.megednan.dailynews.viewmodels.SportViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportFragment : Fragment(R.layout.sport_fragment) {


    private val viewModel by viewModels<SportViewModel>()
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= SportFragmentBinding.bind(view)
        val linearLayoutManager= LinearLayoutManager(context)
        newsAdapter= NewsAdapter()
        viewModel.getNews().observe(viewLifecycleOwner, Observer {
            newsAdapter.submitData(lifecycle, it)
        })
        binding.sportFagRv.apply {
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
                    R.id.action_sportFragment_to_articleFragment,
                    bundle
            )

        }




    }
}