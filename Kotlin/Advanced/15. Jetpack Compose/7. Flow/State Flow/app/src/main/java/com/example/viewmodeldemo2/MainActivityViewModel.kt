package com.example.viewmodeldemo2

import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(startingTotal: Int) : ViewModel() {

    private val _flowTotal = MutableStateFlow<Int>(0)
    val flowTotal: StateFlow<Int> = _flowTotal

    private val _msg = MutableSharedFlow<String>()
    val msg: SharedFlow<String> = _msg

    init {
        _flowTotal.value = startingTotal
    }

    fun setTotal(input: Int) {
        _flowTotal.value = (_flowTotal.value).plus(input)

        viewModelScope.launch {
            _msg.emit("Total updated...")
        }
    }
}

class MainActivityViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainActivityViewModel(startingTotal) as T
}