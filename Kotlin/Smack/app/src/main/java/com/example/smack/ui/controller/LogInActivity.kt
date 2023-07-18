package com.example.smack.ui.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smack.R
import com.example.smack.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}