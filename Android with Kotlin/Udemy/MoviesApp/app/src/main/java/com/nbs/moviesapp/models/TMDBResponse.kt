package com.nbs.moviesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TMDBResponse(
    open val page: Int,
    open val results: List<Movie>,
    @SerializedName("total_pages")
    open val totalPages: Int,
    @SerializedName("total_results")
    open val totalResults: Int
)

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val popularity: Double
)
