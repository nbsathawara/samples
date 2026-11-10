package com.nbs.dailypulse.usecases

import com.nbs.dailypulse.articles.Article
import com.nbs.dailypulse.articles.toEntity
import com.nbs.dailypulse.articles.toModel
import com.nbs.dailypulse.db.ArticleDao
import com.nbs.dailypulse.db.ArticleEntity
import com.nbs.dailypulse.services.ArticleService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleUseCase(
    private val articleService: ArticleService,
    private val articleDao: ArticleDao
) {

    // Observe Room DB -> Map to UI Model
    fun observeArticles(): Flow<List<Article>> =
        articleDao.getArticles().map { entities ->
            entities.map { it.toModel() }
        }

    // Fetch API DTOs -> Map to Room Entity -> Save to Cache
    suspend fun fetchAndCacheArticles() {
        val dtos = articleService.fetchArticles()
        articleDao.clearArticles()
        articleDao.insertArticles(dtos.map { it.toEntity() })
    }
}