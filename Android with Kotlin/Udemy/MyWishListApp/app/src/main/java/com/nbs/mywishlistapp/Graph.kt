package com.nbs.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.nbs.mywishlistapp.data.repositories.WishRepository
import com.nbs.mywishlistapp.database.AppDatabase

object Graph {
    lateinit var appDatabase: AppDatabase

    val wishRepository by lazy {
        WishRepository(appDatabase.wishDao())
    }

    fun provide(context: Context) {
        appDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }
}