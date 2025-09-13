package com.nbs.subsriptionapp.custom

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomSpacer(
    modifier: Modifier = Modifier,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    color: Color = Color.Transparent
) {
    val isVertical = width == 0.dp
    if (isVertical)
        Spacer(
            modifier
                .fillMaxWidth()
                .height(height)
                .width(0.dp)
                .background(color)
        )
    else
        Spacer(
            modifier
                .width(width)
                .height(0.dp)
                .background(color)
        )
}

@Composable
fun CustomButton(text: String = "", onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Composable
fun ErrorText(msg: String = "Invalid Value") {
    Text(text = msg, color = MaterialTheme.colorScheme.error)
}

@Composable
fun NavigationIcon(
    icon: ImageVector,
    contentDesc: String = "",
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    IconButton({
        onClick()
    }) {
        Icon(
            icon, contentDescription = contentDesc,
            tint = iconColor
        )
    }
}

@Composable
fun BackIcon(onClick: () -> Unit) {
    NavigationIcon(
        icon = Icons.AutoMirrored.Default.ArrowBack,
        onClick = {
            onClick()
        })
}

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    confirmText: String,
    dismissText: String,
    icon: ImageVector? = null,
) {
    AlertDialog(
        icon = {
            if (icon == null) null else Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissText)
            }
        }
    )
}