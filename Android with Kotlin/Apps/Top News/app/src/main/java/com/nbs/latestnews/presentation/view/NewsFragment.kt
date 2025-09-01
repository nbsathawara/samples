package com.nbs.latestnews.presentation.view

import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nbs.latestnews.R
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.data.util.Utils
import com.nbs.latestnews.databinding.FragmentNewsBinding
import com.nbs.latestnews.presentation.adapter.NewsAdapter
import com.nbs.latestnews.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : BaseFragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    private var page = Utils.newsPage

    private var isLoading = false
    private var isScrolling = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        initRecyclerView()
        setSearchView()
        getNewsList()
    }

    private fun initRecyclerView() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(scrollListener)
            adapter = newsAdapter
            newsAdapter.setOnItemClick {
                val bundle = Bundle().apply {
                    putSerializable(Utils.keyArticle, it)
                }
                val navController = findNavController()
                navController.navigate(
                    R.id.action_newsFragment_to_newsDetailsFragment, bundle
                )
            }
        }
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i(Utils.logTag, "Query : ${query.toString()}")
                    newsViewModel.searchNews(Utils.newsCountry, query.toString(), page)
                    getSearchedNewsList()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    MainScope().launch {
                        delay(2000)
                        Log.i(Utils.logTag, "Query Text : ${newText.toString()}")
                        newsViewModel.searchNews(Utils.newsCountry, newText.toString(), page)
                        getSearchedNewsList()
                    }
                    return false
                }
            }
        )

        binding.searchView.setOnCloseListener {
            initRecyclerView()
            getNewsList()
            false
        }
    }

    private fun getNewsList() {
        newsViewModel.getNewsHeadlines(Utils.newsCountry, page)
        newsViewModel.newsHeadlines.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    changeProgressBar(false)
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                        val curPage =
                            if (it.totalResults % 20 == 0) it.totalResults / 20 else it.totalResults / 20 + 1

                        isLastPage = curPage == page
                    }
                }
                is Resource.Error -> {
                    changeProgressBar(false)
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    changeProgressBar(true)
                }
                else -> {}
            }
        })
    }

    private fun getSearchedNewsList() {
        if (view == null) return
        newsViewModel.searchedNewsList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    changeProgressBar(false)
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                        val curPage =
                            if (it.totalResults % 20 == 0) it.totalResults / 20 else it.totalResults / 20 + 1

                        isLastPage = curPage == page
                    }
                }
                is Resource.Error -> {
                    changeProgressBar(false)
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    changeProgressBar(true)
                }
                else -> {}
            }
        })
    }

    private fun changeProgressBar(show: Boolean) {
        isLoading = isScrolling
        if (show)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }


    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            val totalItems = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPos = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPos + visibleItems >= totalItems

            val shouldPaginate = !isLoading && !isLastPage && !isScrolling
                    && hasReachedToEnd
            if (shouldPaginate) {
                page++
                newsViewModel.getNewsHeadlines(Utils.newsCountry, page)
                isScrolling = false
            }
        }
    }
}