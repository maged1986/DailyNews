package com.megednan.dailynews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.megednan.dailynews.databinding.LoadStateFooterLayoutBinding

class NewsLoadStateAdapter(private val retry: () -> Unit):LoadStateAdapter<NewsLoadStateAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): NewsHolder {
        val binding=LoadStateFooterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }
    override fun onBindViewHolder(holder: NewsHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class NewsHolder(val binding:LoadStateFooterLayoutBinding)
        :RecyclerView.ViewHolder(binding.root){
        init {
            binding.loadStateBtnRetry.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState: LoadState){
            binding.apply {
                loadStateProgressBar.isVisible = loadState !is LoadState.Loading
                loadStateBtnRetry.isVisible = loadState !is LoadState.Loading
                loadStateTvError.isVisible = loadState !is LoadState.Loading

            }

        }
    }



}