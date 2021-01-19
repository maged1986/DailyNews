package com.megednan.dailynews.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.megednan.dailynews.R
import com.megednan.dailynews.adapters.NewsAdapter
import com.megednan.dailynews.adapters.NewsLoadStateAdapter
import com.megednan.dailynews.databinding.HomeFragmentBinding
import com.megednan.dailynews.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.load_state_footer_layout.view.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {


    private val viewModel by viewModels<HomeViewModel>()
    lateinit var newsAdapter:NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val binding = HomeFragmentBinding.bind(view)
        val linearLayoutManager=LinearLayoutManager(context)
        newsAdapter= NewsAdapter()
        viewModel.getNews().observe(viewLifecycleOwner, Observer {
            newsAdapter.submitData(lifecycle, it)
        })
        binding.homeFragRv.apply {
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
                    R.id.action_homeFragment_to_articleFragment,
                    bundle
            )

        }

    }







}