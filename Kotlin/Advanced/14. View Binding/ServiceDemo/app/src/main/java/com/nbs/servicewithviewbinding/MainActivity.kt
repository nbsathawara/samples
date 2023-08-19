package com.nbs.servicewithviewbinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.nbs.servicewithviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        serviceIntent.putExtra(Utils.keyName, "Nikhil Sathawara")
        serviceIntent.putExtra(Utils.keyMarks, 99)
        binding.apply {
            btnStart.setOnClickListener {
                Log.i(Utils.tagName, "Service Starting...")
                startService(serviceIntent)
            }

            btnStop.setOnClickListener {
                Log.i(Utils.tagName, "Service Stopping...")
                stopService(serviceIntent)
            }
        }
    }
}