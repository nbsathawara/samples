package com.nbs.mywishlistapp.custom

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.nbs.mywishlistapp.data.Constants

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
fun BackIcon() {
    NavigationIcon(
        icon = Icons.AutoMirrored.Default.ArrowBack,
        onClick = {
            Log.d(Constants.AppTag, "Back Clicked....")
        })
}