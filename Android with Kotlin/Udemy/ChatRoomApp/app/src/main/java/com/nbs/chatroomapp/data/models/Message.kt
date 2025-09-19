package com.nbs.chatroomapp.data.models

data class Message(
    val text: String = "",
    val sender: String = "",
    val timestamp: Long = 0
)
