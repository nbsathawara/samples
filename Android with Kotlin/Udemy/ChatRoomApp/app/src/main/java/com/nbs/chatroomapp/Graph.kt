package com.nbs.chatroomapp

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore

object Graph {
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun fireStoreInstance(): FirebaseFirestore {
        return instance
    }

    fun provide(context: Context) {

    }
}