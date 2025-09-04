package com.nbs.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.shoppinglist.custom.CustomSpacer
import com.nbs.shoppinglist.custom.ErrorText


data class ShoppingItem(
    var id: Int = -1, var name: String,
    var qty: Int, var isEditMode: Boolean = false
)

@Composable
fun ShoppingList() {
    var shoppingItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(), Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Button({ showDialog = true }) {
            Text("Add Item")
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(shoppingItems) {

            }
        }
    }
    if (showDialog)
        AddItemDialog(
            {
                shoppingItems += it
            },
            {
                showDialog = false
            })
}

@Composable
fun AddItemDialog(itemAdded: (item: ShoppingItem) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }

    var isInvalidName by remember { mutableStateOf(false) }
    var isInvalidQty by remember { mutableStateOf(false) }

    fun addItem() {
        isInvalidName = name.isBlank();
        isInvalidQty = qty.isBlank() || qty.toIntOrNull() == null;
        if (isInvalidName || isInvalidQty)
            return
        val item = ShoppingItem(
            name = name, qty = qty.toInt()
        )

        name = ""
        qty = ""

        itemAdded(item)
        onDismiss()
    }

    AlertDialog(
        { onDismiss() },
        title = {
            Text("Add Shopping Item")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    singleLine = true,
                    isError = isInvalidName,
                    label = {
                        Text("Item name")
                    },
                    supportingText = {
                        if (isInvalidName)
                            ErrorText()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        name = it
                    }
                )
                CustomSpacer(height = 16.dp)
                OutlinedTextField(
                    value = qty.toString(),
                    singleLine = true,
                    isError = isInvalidQty,
                    label = { Text("Item qty") },
                    supportingText = {
                        if (isInvalidQty)
                            ErrorText()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
                        qty = it
                    }
                )
            }
        },
        confirmButton = {
            Button({ onDismiss() }) {
                Text("Cancel")
            }
        },
        dismissButton = {
            Button({ addItem() }) {
                Text("Add")
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingListPreview() {
    ShoppingList()
}