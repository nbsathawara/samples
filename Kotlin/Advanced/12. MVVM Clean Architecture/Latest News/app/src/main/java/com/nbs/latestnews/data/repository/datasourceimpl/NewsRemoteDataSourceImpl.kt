package com.nbs.latestnews.data.repository.datasourceimpl

import com.nbs.latestnews.data.api.NewsAPIService
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
) :
    NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<NewsAPIResponse> =
        newsAPIService.getTopHeadlines(country, page)
}