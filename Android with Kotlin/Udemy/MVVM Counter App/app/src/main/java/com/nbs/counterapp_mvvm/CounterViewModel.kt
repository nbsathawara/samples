package com.nbs.counterapp_mvvm

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    private val _counterRepository = CounterRepository()
    private val _count = mutableIntStateOf(_counterRepository.getCounter().count)
    val count: MutableIntState = _count

    fun change(shouldIncrease: Boolean = true) {
        _count.intValue = _counterRepository.changeCounter(shouldIncrease)
    }
}