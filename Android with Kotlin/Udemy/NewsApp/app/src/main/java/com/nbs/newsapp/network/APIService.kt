package com.nbs.newsapp.network

import com.nbs.newsapp.models.Post
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

}