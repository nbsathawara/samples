package com.nbs.latestnews.data.repository

import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.repository.datasource.NewsLocalDataSource
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<NewsAPIResponse> =
        responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))


    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<NewsAPIResponse> =
        responseToResource(newsRemoteDataSource.getSearchedNews(country, searchQuery, page))


    override suspend fun saveNews(article: Article) = newsLocalDataSource.saveArticleToDB(article)


    override suspend fun deleteNews(article: Article) = newsLocalDataSource.deleteArticle(article)


    override fun getSavedNews(): Flow<List<Article>> = newsLocalDataSource.getSavedArticles()
    private fun responseToResource(response: Response<NewsAPIResponse>): Resource<NewsAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}