package com.nbs.postsapp.network

import com.nbs.postsapp.models.Post
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}