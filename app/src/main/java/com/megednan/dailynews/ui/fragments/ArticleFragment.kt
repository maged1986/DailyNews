package com.megednan.dailynews.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.megednan.dailynews.R
import com.megednan.dailynews.databinding.ArticleFragmentBinding
import com.megednan.dailynews.models.Article
import com.megednan.dailynews.viewmodels.ArticleViewModel
import com.megednan.dailynews.viewmodels.BusinessViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.article_fragment) {
val args :ArticleFragmentArgs by navArgs()
    private val viewModel by viewModels<ArticleViewModel>()
    lateinit var articleObject: Article


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val binding=ArticleFragmentBinding.bind(view)
        articleObject = args.article
       binding.webView.apply {
            webViewClient = WebViewClient()
           articleObject.url?.let { loadUrl(it) }
        }

        binding.floatingActionButton.setOnClickListener {
            viewModel.saveArticle(articleObject)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}