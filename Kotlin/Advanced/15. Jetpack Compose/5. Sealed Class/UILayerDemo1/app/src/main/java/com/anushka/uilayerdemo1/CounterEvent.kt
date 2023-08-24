package com.anushka.uilayerdemo1

sealed class CounterEvent {

    data class ValueEntered(val value: String) : CounterEvent()

    object CountBtnClicked:CounterEvent()

    object ResetBtnClicked:CounterEvent()

}