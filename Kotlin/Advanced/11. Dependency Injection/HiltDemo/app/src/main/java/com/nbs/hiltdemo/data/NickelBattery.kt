package com.nbs.hiltdemo.data

import android.util.Log
import javax.inject.Inject

class NickelBattery @Inject constructor() : Battery {
    override fun getPower() {
        Log.i("MY TAG", "Power from NickelBattery")
    }
}