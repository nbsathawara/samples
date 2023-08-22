package com.nbs.latestnews.data.repository.datasource

import com.nbs.latestnews.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    fun getSavedArticles(): Flow<List<Article>>

    suspend fun saveArticleToDB(article: Article)

    suspend fun deleteArticle(article: Article)
}