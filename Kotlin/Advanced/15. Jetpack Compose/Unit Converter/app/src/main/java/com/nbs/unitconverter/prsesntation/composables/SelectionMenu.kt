package com.nbs.unitconverter.prsesntation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.nbs.unitconverter.data.Conversion

@Composable
fun SelectionMenu(
    list: List<Conversion>, modifier: Modifier = Modifier,
    convert: (Conversion) -> (Unit)
) {

    var displayText by remember { mutableStateOf("Select the Conversion Type") }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var expanded by remember { mutableStateOf(false) }

    var icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = displayText,
            onValueChange = {
                displayText = it
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(text = "Conversion Type")
            },
            trailingIcon = {
                Icon(
                    icon, contentDescription = "icon",
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                )
            },
            readOnly = true
        )
        Spacer(modifier = Modifier.height(5.dp))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(onClick = {
                    displayText = conversion.desc
                    expanded = false
                    convert(conversion)
                }) {
                    Text(
                        text = conversion.desc,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}