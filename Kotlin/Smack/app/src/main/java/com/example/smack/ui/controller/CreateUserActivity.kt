package com.example.smack.ui.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smack.databinding.ActivityCreateUserBinding
import com.example.smack.databinding.ActivityLogInBinding

class CreateUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun userProfileImgClicked(view: View) {

    }

    fun generateColorClicked(view: View) {

    }


    fun createUserClicked(view: View) {

    }
}