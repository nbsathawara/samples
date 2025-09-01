package com.nbs.latestnews.domain.usecase

import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>> = newsRepository.getSavedNews()
}