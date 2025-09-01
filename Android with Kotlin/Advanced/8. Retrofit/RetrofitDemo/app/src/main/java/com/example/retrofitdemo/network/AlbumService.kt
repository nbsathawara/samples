package com.example.retrofitdemo.network

import com.example.retrofitdemo.model.AlbumItem
import com.example.retrofitdemo.model.Albums
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getAlbumsByUser(@Query("userId") userId: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body albumItem: AlbumItem): Response<AlbumItem>

}