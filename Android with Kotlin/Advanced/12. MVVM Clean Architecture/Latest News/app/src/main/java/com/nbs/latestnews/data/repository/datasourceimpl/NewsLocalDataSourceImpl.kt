package com.nbs.latestnews.data.repository.datasourceimpl

import com.nbs.latestnews.data.db.ArticleDao
import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val articleDao: ArticleDao) : NewsLocalDataSource {

    override suspend fun saveArticleToDB(article: Article) {
        articleDao.saveArticle(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> =
        articleDao.getAllArticles()

    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

}