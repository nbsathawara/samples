package com.nbs.mywishlistapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.nbs.mywishlistapp.data.models.Wish
import com.nbs.mywishlistapp.viewmodels.WishViewModel
import kotlinx.coroutines.launch

@Composable
fun AddWishView(
    id: Long,
    navController: NavController,
    viewModel: WishViewModel
) {
    val isEditMode = id != 0L;
    val title = if (isEditMode) stringResource(id = R.string.update_wish)
    else stringResource(id = R.string.add_wish)

    val scope = rememberCoroutineScope()
    var snackMsg by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }

    fun validate(): Boolean {
        val title = viewModel.wishTitle.trim()
        val desc = viewModel.wishDesc.trim()
        if (title.isEmpty()) {
            snackMsg = "Title cannot be empty"
            return false
        }
        if (desc.isEmpty()) {
            snackMsg = "Description cannot be empty"
            return false
        }
        return true
    }

    fun saveWish() {
        if (validate()) {

            val title = viewModel.wishTitle.trim()
            val desc = viewModel.wishDesc.trim()

            if (isEditMode) {
            } else {
                viewModel.insertWish(Wish(title = title, description = desc))
                snackMsg = "Wish has been created!!!"
            }

            scope.launch {
                snackBarHostState.showSnackbar(
                    message = snackMsg,
                    duration = SnackbarDuration.Short
                )
                navController.navigateUp()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState, Modifier.padding(  8.dp))
        },
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
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
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