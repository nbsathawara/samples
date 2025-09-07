package com.nbs.recipeapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CategoriesResponse(val categories: List<Category>)

@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
) : Parcelable