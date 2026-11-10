package com.nbs.dailypulse.services

import com.nbs.dailypulse.articles.Article
import com.nbs.dailypulse.articles.ArticleDto
import com.nbs.dailypulse.articles.ArticleRequest
import com.nbs.dailypulse.articles.ArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(
    private val httpClient: HttpClient
) {

    suspend fun fetchArticles(articleRequest: ArticleRequest = ArticleRequest()): List<ArticleDto> {
        val response: ArticleResponse = httpClient.get(
            urlString = "https://newsapi.org/v2/top-headlines?country=${articleRequest.country}&category=${articleRequest.category}&apiKey=${articleRequest.apiKey}"
        ).body()
        return response.articles
    }
}