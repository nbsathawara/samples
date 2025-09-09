package com.nbs.mywishlistapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nbs.mywishlistapp.R
import com.nbs.mywishlistapp.custom.BackIcon
import com.nbs.mywishlistapp.custom.CustomSpacer
import com.nbs.mywishlistapp.viewmodels.WishViewModel

@Composable
fun AddWishView(
    id: Long,
    navController: NavController,
    viewModel: WishViewModel
) {
    val isEditMode = id != 0L;
    val title = if (isEditMode) stringResource(id = R.string.update_wish)
    else stringResource(id = R.string.add_wish)

    fun saveWish() {
        if (isEditMode) {
        } else {
        }
        if (viewModel.wishTitle.isEmpty() || viewModel.wishDesc.isEmpty()) {

        } else {

        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title =
                    title,
                navIcon = {
                    BackIcon({
                        navController.navigateUp()
                    })
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomSpacer(height = 16.dp)
            CustomTextField(
                "Title",
                viewModel.wishTitle,
                onValueChange = {
                    viewModel.editWishTitle(it)
                })
            CustomSpacer(height = 16.dp)
            CustomTextField(
                "Description",
                viewModel.wishDesc,
                onValueChange = {
                    viewModel.editWishDesc(it)
                })
            CustomSpacer(height = 16.dp)
            Button({ saveWish() }) {
                Text(title, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun CustomTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            errorTextColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddWishViewPreview() {
    //AddWishView(0)
    //CustomTextField("", "", {})
}