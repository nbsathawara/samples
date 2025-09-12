package com.nbs.subsriptionapp.views.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.nbs.subsriptionapp.custom.CustomButton
import com.nbs.subsriptionapp.custom.CustomSpacer

@Composable
fun MyAccountScreen() {

    var showDetails by remember { mutableStateOf(false) }

    var icon = Icons.AutoMirrored.Filled.KeyboardArrowRight
    if (showDetails)
        icon = Icons.Default.KeyboardArrowDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Default.AccountCircle, "",
                    Modifier.size(36.dp)
                )
                CustomSpacer(width = 8.dp)
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Nikhil Sathawara\nnbsathawara@gmail.com"
                )
                IconButton({
                    showDetails = !showDetails
                }) {
                    Icon(
                        icon, "",
                        Modifier.size(24.dp)
                    )
                }
            }
        }

        if (showDetails)
            Card(
                modifier = Modifier.padding(8.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Change Password",
                        style = MaterialTheme.typography.titleMedium
                    )
                    CustomSpacer(height = 8.dp)
                    OutlinedTextField(
                        value = "",
                        label = { Text("Current Password") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {},
                    )
                    CustomSpacer(height = 8.dp)
                    OutlinedTextField(
                        value = "",
                        label = { Text("New Password") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {},
                    )
                    CustomSpacer(height = 8.dp)
                    OutlinedTextField(
                        value = "",
                        label = { Text("Confirm Password") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = {},
                    )
                    CustomSpacer(height = 8.dp)
                    CustomButton(
                        text = "Submit",
                        onClick = {})
                }
            }
    }
}

@Composable
@Preview
fun MyAccountScreenPreview() {
    MyAccountScreen()
}