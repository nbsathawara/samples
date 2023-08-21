package com.nbs.latestnews.domain.repository

import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<NewsAPIResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<NewsAPIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}