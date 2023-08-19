package com.nbs.hiltdemo.data

import android.util.Log
import javax.inject.Inject

class MemoryCard(memorySize:Int) {
    init {
        Log.i("MYTAG", "Memory Card Constructed with $memorySize MB")
    }

    fun getSpaceAvailablity() {
        Log.i("MYTAG", "Memory space available")
    }
}