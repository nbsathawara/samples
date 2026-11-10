package com.nbs.dailypulse

import android.app.Application
import com.nbs.dailypulse.di.initKoin
import org.koin.android.ext.koin.androidContext

class DailyPulseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@DailyPulseApp)
        }
    }
}