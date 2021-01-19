package com.megednan.dailynews.adapters

import android.transition.TransitionInflater.from
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.megednan.dailynews.databinding.NewsListItemBinding
import com.megednan.dailynews.models.Article
import java.util.Date.from
import javax.inject.Inject

class NewsAdapter @Inject constructor() : PagingDataAdapter<Article, NewsAdapter.ArticleHolder>(NEWS_COMPARATOR) {

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        var article=getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding=NewsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleHolder(binding)
    }
    private var onItemClickListener: ((Article) -> Unit)? = null
    inner class ArticleHolder constructor(val binding: NewsListItemBinding)
        :RecyclerView.ViewHolder(binding.root){

        fun bind(article: Article){
            binding.apply {
                Glide.with(binding.root).load(article.urlToImage).centerCrop().into(binding.newsItemIvImage)
               newsItemTvDes.text=article.description
                newsItemTvPublishDate.text=article.publishedAt
                newsItemTvTitle.text=article.title
                newsItemTvSource.text=article.author
                root.setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
            }
        }
    }
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
    companion object{
        private val NEWS_COMPARATOR= object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
              return  oldItem.id==newItem.id }
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem==newItem }
        }
    }
}