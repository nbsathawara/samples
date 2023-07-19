package com.example.smack.ui.service

import com.example.smack.ui.SmackApp
import com.example.smack.ui.model.Channel

object MessageService {
    val channels = ArrayList<Channel>()

    fun getChannels(): List<Channel> {
        channels.clear()
        val allChannelStr = SmackApp.prefs.channels
        println("All channels $allChannelStr")
        if (!allChannelStr.isNullOrEmpty())
            allChannelStr.split("~").forEach { channelStr ->
                val channelData = channelStr.split("||")
                val channel = Channel(channelData[0], channelData[1], channelData[2])
                channels.add(channel)
            }
        return channels
    }
}