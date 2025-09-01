package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    companion object {
        const val keyWorker = "key_worker"
    }

    override fun doWork(): Result {
        try {
            val count = inputData.getInt(MainActivity.keyCount, 0)
            for (i: Int in 0..count) {
                Log.i("My Tag", "Uploading $i")
            }
            val curDateTime = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())
            val outData = Data.Builder().putString(keyWorker, curDateTime).build()
            return Result.success(outData)
        } catch (e: java.lang.Exception) {
            return Result.failure()
        }
    }
}