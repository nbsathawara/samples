package com.nbs.stopwatchdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nbs.stopwatchdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(updateTime, IntentFilter(Utils.keyUpdatedTime))
        serviceIntent = Intent(applicationContext, StopWatchService::class.java)

        binding.apply {
            btnStart.setOnClickListener {
                if (isStarted)
                    stop()
                else
                    start()
            }

            btnReset.setOnClickListener {
                reset()
            }
        }
    }

    private fun start() {
        isStarted = true
        binding.btnStart.text = "Stop"

        serviceIntent.putExtra(Utils.keyCurTime, time)
        startService(serviceIntent)
    }

    private fun stop() {
        isStarted = false
        binding.btnStart.text = "Start"
        stopService(serviceIntent)
    }

    private fun reset() {
        stop()
        time = 0.0
        binding.tvTime.text = getFormattedTime(time)
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(Utils.keyCurTime, 0.0)
            binding.tvTime.text = getFormattedTime(time)
        }
    }

    private fun getFormattedTime(time: Double): String {
        val timeInt = time.toInt()
        val hours = timeInt % 84600 / 3600
        val minutes = timeInt % 84600 % 3600 / 60
        val seconds = timeInt % 84600 % 3600 % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}