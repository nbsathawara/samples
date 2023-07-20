package com.example.smack.ui.service

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack.ui.SmackApp
import com.example.smack.ui.utilities.USER_DATA_CHANGE
import java.util.*

object UserDataService {

    var id: String = ""
        get() = field
        set(value) {
            field = value
            SmackApp.prefs.userId = value
        }
    var avatarColor: String = ""
        get() = field
        set(value) {
            field = value
            SmackApp.prefs.userAvatarColor = value
        }
    var avatarName: String = ""
        get() = field
        set(value) {
            field = value
            SmackApp.prefs.userAvatar = value
        }
    var email: String = ""
        get() = field
        set(value) {
            field = value
            SmackApp.prefs.userEmail = value
        }
    var name: String = ""
        get() = field
        set(value) {
            field = value
            SmackApp.prefs.userName = value
        }

    fun logIn(
        context: Activity,
        id: String,
        userName: String,
        userEmail: String,
        userAvatar: String,
        avtarColor: String
    ) {
        this.id = id
        name = userName
        email = userEmail
        avatarName = userAvatar
        avatarColor = avtarColor
        SmackApp.prefs.isLoggedIn = true

        val userDataChange = Intent(USER_DATA_CHANGE)
        LocalBroadcastManager.getInstance(context).sendBroadcast(userDataChange)
    }

    fun logout() {
        id = ""
        avatarColor = ""
        avatarName = ""
        email = ""
        name = ""
        SmackApp.prefs.authToken = ""
        SmackApp.prefs.userEmail = ""
        SmackApp.prefs.isLoggedIn = false
//     MessageService.clearMessages()
       MessageService.clearChannels()
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