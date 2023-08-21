package com.nbs.latestnews.domain.usecase

import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(searchQuery: String): Resource<NewsAPIResponse> =
        newsRepository.getSearchedNews(searchQuery)
}