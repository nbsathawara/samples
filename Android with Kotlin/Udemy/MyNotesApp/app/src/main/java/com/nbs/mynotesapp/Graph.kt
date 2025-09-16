package com.nbs.mynotesapp

import android.content.Context
import androidx.room.Room
import com.nbs.mynotesapp.database.AppDatabase
import com.nbs.mynotesapp.repositories.NoteRepository

object Graph {
    lateinit var appDatabase: AppDatabase

    val noteRepository by lazy {
        NoteRepository(appDatabase.noteDao())
    }

    fun provide(context: Context) {
        appDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }
}