package com.nbs.chatroomapp

import android.app.Application

class ChatRoom : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}