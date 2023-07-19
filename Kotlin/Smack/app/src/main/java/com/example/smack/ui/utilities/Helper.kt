package com.example.smack.ui.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.view.inputmethod.InputMethodManager
import com.example.smack.ui.SmackApp
import com.example.smack.ui.model.Channel


fun hideKeyboard(activity: Activity) {
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager

    if (inputManager.isAcceptingText)
        inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)

}

fun addChannel(channel: Channel) {
    var channelStr = channel.name + "||" + channel.desc + "||" + channel.id
    println("New Channel : ${channel.name} : ${channel.desc} : ${channel.id}")
    SmackApp.prefs.channels = if(SmackApp.prefs.channels.isNullOrEmpty()) channelStr
        else SmackApp.prefs.channels + "~" + channelStr
    println("All Channels : ${SmackApp.prefs.channels}")
}