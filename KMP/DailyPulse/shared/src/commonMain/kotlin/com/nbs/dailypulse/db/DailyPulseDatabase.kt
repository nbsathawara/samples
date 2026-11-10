package com.nbs.dailypulse.db

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import kotlinx.coroutines.flow.Flow

// 1. Room Entity
@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)

// 2. Room DAO
@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun clearArticles()
}

// 3. Room Database Definition
@Database(entities = [ArticleEntity::class], version = 1)
@ConstructedBy(DailyPulseDatabaseConstructor::class)
abstract class DailyPulseDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao
}

// Room KMP Constructor Helper
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DailyPulseDatabaseConstructor : RoomDatabaseConstructor<DailyPulseDatabase> {
    override fun initialize(): DailyPulseDatabase
}