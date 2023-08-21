package com.nbs.latestnews.data.repository

import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<NewsAPIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<NewsAPIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResource(response: Response<NewsAPIResponse>): Resource<NewsAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}