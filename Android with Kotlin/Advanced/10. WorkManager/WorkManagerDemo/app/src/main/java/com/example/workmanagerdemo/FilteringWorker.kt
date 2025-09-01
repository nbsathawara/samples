package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class FilteringWorker(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        try {
            for (i: Int in 0..500) {
                Log.i("My Tag", "Filtering $i")
            }
            return Result.success()
        } catch (e: java.lang.Exception) {
            return Result.failure()
        }
    }
}