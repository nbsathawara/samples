package com.anushka.uilayerdemo1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _screenState = mutableStateOf(
        MainScreenState(
            inputValue = "",
            displayResult = "Total is 0.0"
        )
    )
    val screenState: State<MainScreenState> = _screenState

    private val _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var total = 0.0

    private fun addTotal() {
        total += _screenState.value.inputValue.toDouble()
        _screenState.value = _screenState.value.copy(
            displayResult = "Total is $total",
            isCountBtnVisible = false
        )
    }

    private fun resetTotal() {
        total = 0.0
        _screenState.value = _screenState.value.copy(
            displayResult = "Total is $total",
            inputValue = "",
            isCountBtnVisible = false
        )
    }

    fun onEvent(counterEvent: CounterEvent) {
        when (counterEvent) {
            is CounterEvent.ValueEntered -> {
                _screenState.value = _screenState.value.copy(
                    inputValue = counterEvent.value,
                    isCountBtnVisible = true
                )
            }
            is CounterEvent.CountBtnClicked -> {
                addTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMsg("Value added successfully...")
                    )
                }
            }
            is CounterEvent.ResetBtnClicked -> {
                resetTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMsg("Value reset successfully...")
                    )
                }
            }
        }
    }
}