package com.example.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanagerdemo.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnUpload.setOnClickListener {
            setOnetimeWorkReq()
            //setPeriodicWorkReq()
        }
    }

    private fun setPeriodicWorkReq() {
        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<DownloadingWorker>(16, TimeUnit.MINUTES).build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(periodicWorkRequest)
    }

    private fun setOnetimeWorkReq() {
        val workManager = WorkManager.getInstance(this)

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putInt(keyCount, 1000)
            .build()


        val uploadReq = OneTimeWorkRequestBuilder<UploadWorker>()
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        val filterReq = OneTimeWorkRequestBuilder<FilteringWorker>()
            .build()
        val compressReq = OneTimeWorkRequestBuilder<CompressingWorker>()
            .build()
        val downloadReq = OneTimeWorkRequestBuilder<DownloadingWorker>()
            .build()

        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadReq)
        parallelWorks.add(filterReq)

        workManager.beginWith(parallelWorks).then(compressReq).then(uploadReq).enqueue()

        workManager.getWorkInfoByIdLiveData(uploadReq.id).observe(this, Observer {
            binding.tvMsg.text = it.state.name
            if (it.state.isFinished) {
                val dataMsg = it.outputData.getString(UploadWorker.keyWorker)
                Toast.makeText(applicationContext, dataMsg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        const val keyCount = "key_count"
    }
}