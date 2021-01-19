package com.megednan.dailynews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.megednan.dailynews.R
import com.megednan.dailynews.adapters.NewsAdapter
import com.megednan.dailynews.adapters.NewsLoadStateAdapter
import com.megednan.dailynews.databinding.FragmentSearchBinding
import com.megednan.dailynews.viewmodels.CultureViewModel
import com.megednan.dailynews.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel by viewModels<SearchViewModel>()

    lateinit var newsAdapter: NewsAdapter
    val args: SearchFragmentArgs by navArgs()
    lateinit var binding: FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
            val query = args.searchItem
        newsAdapter=NewsAdapter()

            viewModel.getNews(query).observe(viewLifecycleOwner, Observer {
                newsAdapter.submitData(lifecycle, it)
            })
            binding.searchFragRv.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                itemAnimator = null
                adapter = newsAdapter.withLoadStateHeaderAndFooter(
                        header = NewsLoadStateAdapter { newsAdapter.retry() }, footer = NewsLoadStateAdapter { newsAdapter.retry() })
            }
            newsAdapter.setOnItemClickListener {
                val bundle = Bundle().apply {
                    putSerializable("article", it)
                }
                findNavController().navigate(
                        R.id.action_cultureFragment_to_articleFragment,
                        bundle
                )
            }


    }

}