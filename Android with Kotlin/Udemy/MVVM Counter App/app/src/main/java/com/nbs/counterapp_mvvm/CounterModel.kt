package com.nbs.counterapp_mvvm

data class CounterModel(var count: Int)

class CounterRepository {
    private var _counter = CounterModel(0)

    fun getCounter() = _counter

    fun changeCounter(shouldIncrease: Boolean = true): Int {
        if (shouldIncrease) _counter.count++
        else {
            _counter.count--
            if (_counter.count < 0)
                _counter.count = 0
        }
        return _counter.count
    }
}