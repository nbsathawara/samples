package com.nbs.recipeapp.models

data class CategoriesResponse(val categories: List<Category>)

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)