package com.anushka.uilayerdemo1

sealed class UIEvent {

    data class ShowMsg(val msg: String) : UIEvent()

}