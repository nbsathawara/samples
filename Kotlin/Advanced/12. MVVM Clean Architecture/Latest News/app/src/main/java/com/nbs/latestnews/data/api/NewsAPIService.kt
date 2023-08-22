package com.nbs.latestnews.data.api

import com.nbs.latestnews.BuildConfig
import com.nbs.latestnews.data.model.NewsAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWS_API_KEY
    ): Response<NewsAPIResponse>
    @GET("top-headlines")
    suspend fun getSearchedNews(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWS_API_KEY
    ): Response<NewsAPIResponse>
}