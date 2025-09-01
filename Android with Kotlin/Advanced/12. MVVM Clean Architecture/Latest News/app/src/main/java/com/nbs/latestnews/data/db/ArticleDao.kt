package com.nbs.latestnews.data.db

import android.database.sqlite.SQLiteDatabase
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nbs.latestnews.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("select * from articles order by id desc")
    fun getAllArticles(): Flow<List<Article>>

    @Insert(onConflict = SQLiteDatabase.CONFLICT_REPLACE)
    suspend fun saveArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)
}