package com.example.smack.ui

import android.app.Application
import com.example.smack.ui.utilities.SharedPrefs

class SmackApp : Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}