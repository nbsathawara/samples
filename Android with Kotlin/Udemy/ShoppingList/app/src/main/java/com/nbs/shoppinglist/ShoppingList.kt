package com.nbs.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

//    shoppingItems += ShoppingItem(222, "Sample", 111)
//    shoppingItems += ShoppingItem(222, "Sample 1", 222, false)

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
            items(shoppingItems) { item ->
                if (item.isEditMode)
                    ShoppingListItemEditor(
                        item,

                        { editedName, editedQty ->
                            shoppingItems = shoppingItems.map { it.copy(isEditMode = false) }
                            val editedItem = shoppingItems.find { it.id == item.id }
                            editedItem?.let {
                                it.name = editedName
                                it.qty = editedQty
                            }
                        },
                        {
                            shoppingItems = shoppingItems.map { it.copy(isEditMode = false) }
                        })
                else
                    ShoppingListItem(
                        item,
                        {
                            shoppingItems =
                                shoppingItems.map { it.copy(isEditMode = it.id == item.id) }
                        },
                        {
                            shoppingItems -= item
                        })
            }
        }
    }
    if (showDialog)
        AddItemDialog(
            {
                it.id = shoppingItems.size + 1
                shoppingItems += it
            },
            {
                showDialog = false
            })
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(2.dp, Color(0XFF018786)),
                RoundedCornerShape(20)
            )
    ) {
        BasicTextField(
            value = item.name,
            enabled = false,
            textStyle = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(8.dp),
            onValueChange = {}
        )
        BasicTextField(
            value = item.qty.toString(),
            enabled = false,
            textStyle = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(8.dp),
            onValueChange = {}
        )

        Row {
            IconButton({
                onEdit()
            }) {
                Icon(Icons.Default.Edit, "")
            }
            IconButton({
                onDelete()
            }) {
                Icon(Icons.Default.Delete, "")
            }
        }
    }
}


@Composable
fun ShoppingListItemEditor(
    item: ShoppingItem,
    onSave: (name: String, qty: Int) -> Unit,
    onCancel: () -> Unit,
) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQty by remember { mutableStateOf(item.qty.toString()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(2.dp, Color(0XFF018786)),
                RoundedCornerShape(20)
            )
    ) {
        BasicTextField(
            value = editedName,
            textStyle = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(8.dp),
            onValueChange = {
                editedName = it
            })
        BasicTextField(
            value = editedQty.toString(),
            modifier = Modifier.padding(8.dp),
            onValueChange = {
                editedQty = it
            })
        Row {
            IconButton({
                onSave(editedName, editedQty.toIntOrNull() ?: 1)
            }) {
                Icon(Icons.Default.Done, "")
            }
            IconButton({
                onCancel()
            }) {
                Icon(Icons.Default.Close, "")
            }
        }
    }
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
//    ShoppingListItem(ShoppingItem(1, "Nikhil", 122), { }, {})
}