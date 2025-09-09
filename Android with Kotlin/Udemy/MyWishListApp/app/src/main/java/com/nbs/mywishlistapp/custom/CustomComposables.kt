package com.nbs.mywishlistapp.custom

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nbs.mywishlistapp.data.Constants

@Composable
fun CustomSpacer(modifier: Modifier = Modifier, width: Dp = 0.dp, height: Dp = 0.dp) {
    val isVertical = width == 0.dp
    if (isVertical)
        Spacer(modifier.height(height))
    else
        Spacer(modifier.width(width))
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