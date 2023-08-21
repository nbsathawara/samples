package com.nbs.latestnews.data.repository.datasource

import com.nbs.latestnews.data.model.NewsAPIResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(country: String, page: Int): Response<NewsAPIResponse>
}