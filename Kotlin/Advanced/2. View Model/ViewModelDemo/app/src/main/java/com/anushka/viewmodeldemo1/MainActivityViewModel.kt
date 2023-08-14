package com.anushka.viewmodeldemo1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModel(private val startingTotal: Int) : ViewModel() {

    private var total = 0;

    init {
        total = startingTotal
    }

    fun getTotal(): Int {
        return total;
    }

    fun add(number: Int?) {
        if (number != null)
            total += number

    }

    class MainActivityViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
                return MainActivityViewModel(startingTotal) as T
            return super.create(modelClass)
        }
    }
}

