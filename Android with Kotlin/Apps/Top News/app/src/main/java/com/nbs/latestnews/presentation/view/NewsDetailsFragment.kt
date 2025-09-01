package com.nbs.latestnews.presentation.view

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.nbs.latestnews.R
import com.nbs.latestnews.databinding.FragmentNewsDetailsBinding
import com.nbs.latestnews.presentation.viewmodel.NewsViewModel

class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    private lateinit var newsViewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)

        newsViewModel = (activity as MainActivity).newsViewModel

        val args: NewsDetailsFragmentArgs by navArgs()
        val article = args.selectedArticle

        binding.webView.apply {
            webViewClient = webClient
            if (!article.url.isNullOrEmpty())
                loadUrl(article.url)
        }

        binding.floatingActionButton.visibility =
            if (article.id > 0) View.GONE else View.VISIBLE
        binding.floatingActionButton.setOnClickListener {
            newsViewModel.saveArticle(article)
            Snackbar.make(it, "Saved Successfully.", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun changeProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private val webClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            changeProgressBar(true)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            changeProgressBar(false)
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            changeProgressBar(false)
        }
    }

}