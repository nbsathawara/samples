package com.example.smack.ui.model

class Channel(name: String, desc: String, val id: String) {
    val name = name
    val desc = desc
    override fun toString(): String {
        return "#$name"
    }

}