package com.nbs.postsapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.postsapp.models.Post
import com.nbs.postsapp.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        try {
            viewModelScope.launch {
                val posts = repository.getPosts()
                _posts.value = posts
            }
        } catch (e: Exception) {
            // Handle error
        }
    }
}