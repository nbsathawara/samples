package com.nbs.newsapp.models

import com.nbs.newsapp.network.RetrofitClient

class PostRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getPosts() = apiService.getPosts()
}