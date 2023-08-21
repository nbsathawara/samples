package com.nbs.latestnews.domain.usecase

import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.domain.repository.NewsRepository

class DeleteSaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)

}