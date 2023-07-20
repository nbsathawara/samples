package com.example.smack.ui.utilities

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import com.example.smack.ui.SmackApp
import com.example.smack.ui.model.Channel
import com.example.smack.ui.model.Message
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


fun hideKeyboard(activity: Activity) {
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager

    if (inputManager.isAcceptingText)
        inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)

}


fun getCurrentDateTime(): String {
    val formatter  =  SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
    return formatter.format(Date())

}

fun addChannel(channel: Channel) {
    var channelStr = channel.name + "||" + channel.desc + "||" + channel.id
    println("New Channel : ${channel.name} : ${channel.desc} : ${channel.id}")
    SmackApp.prefs.channels = if (SmackApp.prefs.channels.isNullOrEmpty()) channelStr
    else SmackApp.prefs.channels + "~" + channelStr
    println("All Channels : ${SmackApp.prefs.channels}")
}

fun addMessage(msg: Message) {
    var msgStr =
        msg.message + "||" + msg.userName + "||" + msg.channelId + "||" + msg.userAvatar + "||" + msg.userAvatarColor + "||" + msg.id + "||" + msg.timeStamp
    println("New Channel : $msgStr")
    SmackApp.prefs.messages = if (SmackApp.prefs.messages.isNullOrEmpty()) msgStr
    else SmackApp.prefs.messages + "~" + msgStr
    println("All Messages : ${SmackApp.prefs.messages}")
}