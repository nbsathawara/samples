package com.nbs.newsapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.newsapp.models.Post
import com.nbs.newsapp.models.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()
    private var _posts by mutableStateOf(emptyList<Post>())
    val posts: List<Post>
        get() = _posts

    init {
        viewModelScope.launch {
            _posts = repository.getPosts()
        }
    }
}