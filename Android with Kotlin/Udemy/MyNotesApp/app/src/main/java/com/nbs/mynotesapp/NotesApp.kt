package com.nbs.mynotesapp

import android.app.Application

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(context = this)
    }
}