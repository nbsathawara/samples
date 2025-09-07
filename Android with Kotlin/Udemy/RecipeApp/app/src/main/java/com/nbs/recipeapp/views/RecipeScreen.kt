package com.nbs.recipeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.nbs.recipeapp.models.Category
import com.nbs.recipeapp.viewmodels.MainViewModel

@Composable
fun RecipeScreen(modifier: Modifier = Modifier, navigateToDetails: (Category) -> Unit) {
    val recipeViewModel: MainViewModel = viewModel()
    val recipeState by recipeViewModel.categoriesState

    Box(modifier = modifier.fillMaxSize()) {
        when {
            recipeState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            recipeState.error != null -> {
                Text("Error fetching categories")
            }

            else -> {
                CategoriesScreen(recipeState.categories, navigateToDetails)
            }
        }
    }
}

@Composable
fun CategoriesScreen(categories: List<Category>, navigateToDetails: (Category) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(2), Modifier.fillMaxSize()) {
        items(categories) {
            CategoryItem(it, navigateToDetails)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navigateToDetails: (Category) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                navigateToDetails(category)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(4.dp)

        )
    }
}
