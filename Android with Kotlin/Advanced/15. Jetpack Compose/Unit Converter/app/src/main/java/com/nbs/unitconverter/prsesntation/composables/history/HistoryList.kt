package com.nbs.unitconverter.prsesntation.composables.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.nbs.unitconverter.database.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onClose: (ConversionResult) -> (Unit)
) {

    LazyColumn {
        items(
            items = list.value,
            key = { item -> item.id }
        ) { item ->
            HistoryItem(msg1 = item.msg1, msg2 = item.msg2, onClose = { onClose(item) })
        }
    }
}