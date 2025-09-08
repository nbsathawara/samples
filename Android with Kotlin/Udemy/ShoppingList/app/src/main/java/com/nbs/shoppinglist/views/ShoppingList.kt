package com.nbs.shoppinglist.views

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nbs.shoppinglist.MainActivity
import com.nbs.shoppinglist.custom.CustomSpacer
import com.nbs.shoppinglist.custom.ErrorText
import com.nbs.shoppinglist.custom.RequestPermission
import com.nbs.shoppinglist.data.Constants
import com.nbs.shoppinglist.data.Screens
import com.nbs.shoppinglist.utils.LocationUtils
import com.nbs.shoppinglist.utils.PermissionUtils
import com.nbs.shoppinglist.viewmodels.LocationViewModel


data class ShoppingItem(
    var id: Int = -1,
    var name: String,
    var qty: Int,
    var address: String = "",
    var isEditMode: Boolean = false
)

@Composable
fun ShoppingList(
    context: Context,
    permissionUtils: PermissionUtils,
    locationUtils: LocationUtils,
    navController: NavController,
    viewModel: LocationViewModel,
    address: String
) {
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
            context,
            address,
            permissionUtils,
            locationUtils,
            navController, viewModel,
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
        Column(
            Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Row {
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
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, "")
                BasicTextField(
                    value = item.address,
                    enabled = false,
                    textStyle = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(8.dp),
                    onValueChange = {}
                )
            }
        }

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


fun goToLocationSelection(
    context: Context,
    locationUtils: LocationUtils,
    navController: NavController,
    viewModel: LocationViewModel
) {
    locationUtils.requestLocationUpdates(context, viewModel)
    navController.navigate(Screens.LocationSelectionScreen.name) {
        this.launchSingleTop
    }
}

@Composable
fun AddItemDialog(
    context: Context,
    address: String,
    permissionUtils: PermissionUtils,
    locationUtils: LocationUtils,
    navController: NavController,
    viewModel: LocationViewModel,
    itemAdded: (item: ShoppingItem) -> Unit, onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }

    var isInvalidName by remember { mutableStateOf(false) }
    var isInvalidQty by remember { mutableStateOf(false) }

    val requestedPermissions = listOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { grantedPermissions ->
            val allPermissionsGranted = requestedPermissions.all {
                grantedPermissions[it] == true
            }
            if (allPermissionsGranted) {
                goToLocationSelection(context, locationUtils, navController, viewModel)
            } else {
                val deniedPermission = grantedPermissions.entries.first { !it.value }
                permissionUtils.showRationale(
                    context as MainActivity,
                    deniedPermission.key,
                    "Location is required!!"
                )
            }
        }
    )

    fun addItem() {
        isInvalidName = name.isBlank();
        isInvalidQty = qty.isBlank() || qty.toIntOrNull() == null;
        if (isInvalidName || isInvalidQty)
            return

        val item = ShoppingItem(
            name = name,
            qty = qty.toInt(),
            address = address
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
                CustomSpacer(height = 16.dp)
                Button({
                    if (permissionUtils.hasLocationPermission(context)) {
                        goToLocationSelection(context, locationUtils, navController, viewModel)
                    } else {
                        requestPermissionLauncher.launch(requestedPermissions.toTypedArray())
                    }
                }) {
                    Text("Add Address")
                }
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
    //ShoppingList()
    ShoppingListItem(
        ShoppingItem(
            1,
            "Nikhil",
            122,
            "7 Asarwa Society Asarwa Ahmedabad gujarat 380016"
        ), { }, {})
}