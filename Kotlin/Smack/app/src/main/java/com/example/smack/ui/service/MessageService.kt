package com.example.smack.ui.service

import com.example.smack.ui.SmackApp
import com.example.smack.ui.model.Channel
import com.example.smack.ui.model.Message

object MessageService {
    val channels = ArrayList<Channel>()
    val messages = ArrayList<Message>()

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

    fun getMessages(channel: Channel): List<Message> {
        messages.clear()
        val allMessageStr = SmackApp.prefs.messages
        println("All mesgs $allMessageStr")
        if (!allMessageStr.isNullOrEmpty())
            allMessageStr.split("~").forEach { msgStr ->
                val msgData = msgStr.split("||")
                val channelId = msgData[2]
                if (channelId == channel.id) {
                    val message = Message(
                        msgData[0], msgData[1], channelId,
                        msgData[3], msgData[4], msgData[5], msgData[6]
                    )
                    messages.add(message)
                }
            }
        return messages
    }

    fun clearChannels() {
        channels.clear()
    }

    fun clearMessages() {
        messages.clear()
    }
}