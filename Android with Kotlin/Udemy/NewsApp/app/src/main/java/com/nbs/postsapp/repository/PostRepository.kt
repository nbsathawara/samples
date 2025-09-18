package com.nbs.postsapp.repository

import com.nbs.postsapp.network.APIService
import com.nbs.postsapp.network.RetrofitClient
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: APIService) {
    suspend fun getPosts() = apiService.getPosts()

}