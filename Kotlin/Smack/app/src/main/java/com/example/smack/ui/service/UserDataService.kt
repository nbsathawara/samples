package com.example.smack.ui.service

import android.graphics.Color
import com.example.smack.ui.SmackApp
import java.util.*

object UserDataService {

    var id = ""
    var avatarColor = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun logout() {
        id = ""
        avatarColor = ""
        avatarName = ""
        email = ""
        name = ""
        SmackApp.prefs.authToken = ""
        SmackApp.prefs.userEmail = ""
        SmackApp.prefs.isLoggedIn = false
//        MessageService.clearMessages()
//        MessageService.clearChannels()
    }

    fun returnAvatarColor(components: String): Int {
        val colors = components
            .replace("[", "")
            .replace("]", "")
            .split(",")


        var r = (colors[0].toDouble() * 255).toInt()
        var g = (colors[1].toDouble() * 255).toInt()
        var b = (colors[2].toDouble() * 255).toInt()

        return Color.rgb(r, g, b)
    }
}