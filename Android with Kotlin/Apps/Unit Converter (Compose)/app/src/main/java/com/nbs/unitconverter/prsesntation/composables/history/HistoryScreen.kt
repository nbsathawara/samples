package com.nbs.unitconverter.prsesntation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nbs.unitconverter.database.ConversionResult

@Composable
fun HistoryScreen(
    list: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onRemoveAll: () -> (Unit),
    onRemove: (ConversionResult) -> (Unit)
) {
    Column(
    ) {
        if (list.value.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()

            ) {
                Text(text = "Conversion History", color = Color.Black)
                OutlinedButton(
                    onClick = {
                        onRemoveAll()
                    }
                ) {
                    Text(text = "Delete All", color = Color.Red)
                }
            }
        }
        HistoryList(list = list) {
            onRemove(it)
        }
    }
}