package com.example.smack.ui.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack.R
import com.example.smack.databinding.ActivityLogInBinding
import com.example.smack.ui.SmackApp
import com.example.smack.ui.service.UserDataService
import com.example.smack.ui.utilities.USER_DATA_CHANGE
import com.example.smack.ui.utilities.hideKeyboard
import java.util.Random

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableSpinner(false)

        binding.btnLogIn.setOnClickListener {
            hideKeyboard(this)
            enableSpinner(true)
            createUser()
            SmackApp.prefs.isLoggedIn = true
            val userDataChange = Intent(USER_DATA_CHANGE)
            LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)
            //Thread.sleep(2000)
            enableSpinner(false)
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, CreateUserActivity::class.java))
            finish()
        }
    }

    fun createUser() {

        var userAvatar = "profileDefault"
        var avtarColor = "[05,0.5,0.5,1]"

        val color = random.nextInt(2)
        val avatarIndex = random.nextInt(28)

        if (color == 0)
            userAvatar = "light$avatarIndex"
        else
            userAvatar = "dark$avatarIndex"

        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        avtarColor = "[${r.toDouble() / 255},${g.toDouble() / 255},${b.toDouble() / 255},1]"

        val email = binding.etEmail.text.toString()

        UserDataService.email = email
        UserDataService.name = if (email.contains("@")) email.substring(0, email.indexOf('@'))
        else email
        UserDataService.id = random.nextInt(1000).toString()
        UserDataService.avatarName = userAvatar
        UserDataService.avatarColor = avtarColor
    }

    fun enableSpinner(enable: Boolean) {
        if (enable)
            binding.spinnerLogInUser.visibility = View.VISIBLE
        else binding.spinnerLogInUser.visibility = View.GONE
        binding.btnLogIn.isEnabled = !enable
        binding.btnSignUp.isEnabled = !enable
    }
}