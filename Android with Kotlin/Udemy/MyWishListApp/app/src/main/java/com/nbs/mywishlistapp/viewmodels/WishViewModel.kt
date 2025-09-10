package com.nbs.mywishlistapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.mywishlistapp.Graph
import com.nbs.mywishlistapp.data.models.Wish
import com.nbs.mywishlistapp.data.repositories.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
) : ViewModel() {

    var wishTitle by mutableStateOf("")
    var wishDesc by mutableStateOf("")
    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWish()
        }
    }

    fun getWish(id: Long): Flow<Wish> {
        return wishRepository.getWish(id)
    }

    fun insertWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.insertWish(wish)
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
}