package com.example.smack.ui.controller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smack.databinding.ActivityCreateUserBinding
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

    }
}