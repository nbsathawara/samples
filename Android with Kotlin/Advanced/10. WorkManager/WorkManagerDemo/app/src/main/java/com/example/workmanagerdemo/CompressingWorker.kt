package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class CompressingWorker(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        try {
            for (i: Int in 0..1000) {
                Log.i("My Tag", "Compressing $i")
            }
            return Result.success()
        } catch (e: java.lang.Exception) {
            return Result.failure()
        }
    }
}