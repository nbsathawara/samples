package com.nbs.mynotesapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.nbs.mynotesapp.models.Note
import com.nbs.subsriptionapp.custom.CustomSpacer

@Composable
fun AddNoteScreen(
    showDialog: Boolean = false,
    onDismiss: () -> Unit,
    onNoteAdded: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val defaultColor = MaterialTheme.colorScheme.primary
    var selectedColor by remember { mutableStateOf(defaultColor) }

    fun clear() {
        title = ""
        description = ""
        selectedColor = defaultColor
    }

    if (showDialog)
        AlertDialog(
            onDismissRequest = { /* Handle dismiss if needed */ },
            title = { Text("Add Note") },
            text = {
                Column(modifier = Modifier.wrapContentSize()) {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") }
                    )
                    CustomSpacer(height = 8.dp)
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") }
                    )
                    CustomSpacer(height = 8.dp)
                    ColorPicker(
                        selectedColor = selectedColor,
                        onColorSelected = { selectedColor = it }
                    )
                }
            },
            confirmButton = {
                Button({
                    val newNote = Note(
                        title = title,
                        description = description,
                        color = selectedColor.toArgb()
                    )
                    onNoteAdded(newNote)
                    clear()
                }) {
                    Text("Save Note")
                }
            },
            dismissButton = {
                Button({
                    clear()
                    onDismiss()
                }) {
                    Text("Cancel")
                }
            }
        )
}

@Composable
fun ColorPicker(selectedColor: Color, onColorSelected: (Color) -> Unit) {
    // Colors List
    val colorsList = listOf(
        MaterialTheme.colorScheme.primary,
        Color("#f59597".toColorInt()),
        Color("#f38588".toColorInt()),
        Color("#8db8e3".toColorInt()),
        Color("#c09cc8".toColorInt()),
        Color("#9999cd".toColorInt()),
        Color("#9fd5be".toColorInt()),
        Color("#dfe581".toColorInt()),
        Color("#e2eb92".toColorInt()),
        Color("#faa385".toColorInt())
    )

    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(colorsList) { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .clip(
                        CircleShape
                    )
                    .border(
                        width = if (color == selectedColor) 2.dp else 0.dp,
                        color = if (color == selectedColor) Color.Black else Color.Transparent,
                        shape = CircleShape
                    )
                    .background(color)
                    .clickable {
                        onColorSelected(color)
                    }
            )
        }
    }
}

@Preview
@Composable
fun AddNoteScreenPreview() {
    //AddNoteScreen()
    //ColorPicker(selectedColor = Color.Red, onColorSelected = {})
}