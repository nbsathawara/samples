package com.nbs.latestnews.presentation.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbs.latestnews.data.db.ArticleDao
import com.nbs.latestnews.data.db.NewsDatabase
import com.nbs.latestnews.data.util.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesNewsDatabase(app: Application): NewsDatabase =
        Room.databaseBuilder(app, NewsDatabase::class.java, Utils.dbName)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesArticleDao(newsDatabase: NewsDatabase): ArticleDao =
        newsDatabase.getArticleDao()

}