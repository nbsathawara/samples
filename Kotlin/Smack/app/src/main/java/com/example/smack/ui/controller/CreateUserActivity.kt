package com.example.smack.ui.controller

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack.databinding.ActivityCreateUserBinding
import com.example.smack.ui.SmackApp
import com.example.smack.ui.service.UserDataService
import com.example.smack.ui.utilities.SharedPrefs
import com.example.smack.ui.utilities.USER_DATA_CHANGE
import com.example.smack.ui.utilities.hideKeyboard
import java.util.Random

class CreateUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateUserBinding
    val random = Random()
    var userAvatar = "profileDefault"
    var avtarColor = "[05,0.5,0.5,1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableSpinner(false)
    }

    fun generateAvatarClicked(view: View) {
        val color = random.nextInt(2)
        val avatarIndex = random.nextInt(28)

        if (color == 0)
            userAvatar = "light$avatarIndex"
        else
            userAvatar = "dark$avatarIndex"

        binding.imgProfile.setImageResource(
            resources.getIdentifier(
                userAvatar,
                "drawable",
                packageName
            )
        )

    }

    fun generateColorClicked(view: View) {
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        avtarColor = "[${r.toDouble() / 255},${g.toDouble() / 255},${b.toDouble() / 255},1]"
        binding.imgProfile.setBackgroundColor(Color.rgb(r, g, b))
    }


    fun createUserClicked(view: View) {
        hideKeyboard(this)
        enableSpinner(true)

        val userName = binding.etUserName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        UserDataService.name = userName
        UserDataService.email = email
        UserDataService.avatarName = userAvatar
        UserDataService.avatarColor = avtarColor
        UserDataService.id = random.nextInt(1000).toString()
        SmackApp.prefs.isLoggedIn = true

        val userDataChange = Intent(USER_DATA_CHANGE)
        LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)
        Thread.sleep(2000)
        enableSpinner(false)
        finish()
    }

    fun enableSpinner(enable: Boolean) {
        binding.spinnerCreateUser.bringToFront()
        if (enable)
            binding.spinnerCreateUser.visibility = View.VISIBLE
        else binding.spinnerCreateUser.visibility = View.INVISIBLE

        binding.btnCreateUser.isEnabled = !enable
        binding.btnUserColor.isEnabled = !enable
        binding.imgProfile.isEnabled = !enable
    }
}