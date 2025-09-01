package com.nbs.latestnews.domain.usecase

import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, page: Int): Resource<NewsAPIResponse> =
        newsRepository.getNewsHeadlines(country, page)

}