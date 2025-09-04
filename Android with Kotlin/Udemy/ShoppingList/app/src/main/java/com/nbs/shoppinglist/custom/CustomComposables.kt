package com.nbs.shoppinglist.custom

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(width: Dp = 0.dp, height: Dp = 0.dp, modifier: Modifier = Modifier) {
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