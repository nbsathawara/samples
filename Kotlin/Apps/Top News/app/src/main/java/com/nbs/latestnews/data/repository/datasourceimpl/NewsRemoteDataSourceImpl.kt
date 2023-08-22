package com.nbs.latestnews.data.repository.datasourceimpl

import android.util.Log
import com.nbs.latestnews.data.api.NewsAPIService
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import com.nbs.latestnews.data.util.Utils
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
) :
    NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<NewsAPIResponse> {
        Log.i(Utils.logTag, "Getting News : $country : $page")
        return newsAPIService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<NewsAPIResponse> {
        Log.i(Utils.logTag, "Searching News : $country : $searchQuery : $page")
        return newsAPIService.getSearchedNews(country, page, searchQuery)
    }

}