package com.nbs.dailypulse

import android.content.res.Resources
import android.os.Build
import android.util.Log

class AndroidPlatform : Platform {
    override val osName: String = "Android"
    override val osVersion: String = Build.VERSION.SDK_INT.toString()
    override val deviceModel: String = "${Build.MANUFACTURER} ${Build.MODEL}"
    override val density: Int = (Resources.getSystem().displayMetrics.density).toInt()

    override fun logSystemInfo() {
        Log.d("Daily Pulse", "($osName,$osVersion,$deviceModel,$density)")
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()