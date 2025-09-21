package com.nbs.chatroomapp.data.models

data class Message(
    val id: String = "",
    var text: String = "",
    val sender: String = "",
    var isEdited: Boolean = false,
    var timestamp: Long = 0
)
