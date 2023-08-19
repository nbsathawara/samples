package com.nbs.servicewithviewbinding

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    init {
        Log.i(Utils.tagName, "Service has been created...")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(Utils.tagName, "Service has been started...")
        val name = intent?.getStringExtra(Utils.keyName)
        val marks = intent?.getIntExtra(Utils.keyMarks, -1)
        Log.i(Utils.tagName, "Name : $name & Marks : $marks")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i(Utils.tagName, "Service has been destroyed...")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

}