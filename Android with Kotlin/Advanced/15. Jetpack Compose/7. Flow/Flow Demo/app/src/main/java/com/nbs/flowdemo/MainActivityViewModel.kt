package com.nbs.flowdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    init {
        backPressureDemo()
    }

    private fun backPressureDemo() {

        val myFlow = flow {
            for (i in 1..10) {
                Log.i("MyTag", "Produced : $i")
                emit(i)
                delay(1000)
            }
        }

        viewModelScope.launch {
            myFlow.filter {
                it % 4 == 0
            }.map {
                showMsg(it)
            }.collect {
                Log.i("MyTag", "Consumed : $it")
            }
        }
    }

    private fun showMsg(count: Int): String {
        return "Hello, $count"
    }
}