package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.anushka.coroutinesdemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            btnCount.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    updateCount()
                }
            }
            btnDownloadUserData.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    //downloadUserData()
                    //tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
                    tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
                }
            }
        }
    }

    private fun updateCount() {
        binding.tvCount.text = count++.toString()
        val msg = "Updating count using ${Thread.currentThread().name}"
        Log.i("MyTag", msg)
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main) {
                val msg = "Downloading user $i in ${Thread.currentThread().name}"
                binding.tvUserMessage.text = msg
                //Log.i("MyTag", msg)
            }
        }
    }
}