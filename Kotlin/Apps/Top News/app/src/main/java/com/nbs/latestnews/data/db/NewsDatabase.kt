package com.nbs.latestnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.nbs.latestnews.data.model.Article

@Database(
    version = 1,
    entities = [Article::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao
}