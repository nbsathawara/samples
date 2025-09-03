package com.nbs.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("1.0") }
    var outputValue by remember { mutableStateOf("1.0") }

    var sourceValue by remember { mutableStateOf("Kilometers") }
    var destValue by remember { mutableStateOf("Kilometers") }

    var sourceExpanded by remember { mutableStateOf(false) }
    var destExpanded by remember { mutableStateOf(false) }

    var sourceRatio by remember { mutableDoubleStateOf(1.0) }
    var destRatio by remember { mutableDoubleStateOf(1.0) }

    fun convertUnits() {
        outputValue = (((inputValue.toDoubleOrNull()
            ?: 0.0) * sourceRatio * 100.0 / destRatio).roundToInt() / 100.0).toString()
    }

    fun onValueChanged(isSource: Boolean, value: String, ratio: Double) {
        if (isSource) {
            sourceValue = value
            sourceRatio = ratio
        } else {
            destValue = value
            destRatio = ratio
        }
        convertUnits()
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
        )
        CustomSpacer(height = 24.dp)
        OutlinedTextField(
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            label = { Text("Enter value...") },
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            }
        )
        CustomSpacer(height = 24.dp)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Dropdown(
                sourceExpanded,
                sourceValue,
                onExpand = {
                    sourceExpanded = it
                },
                onValueChange = { value: String, ratio: Double ->
                    onValueChanged(
                        true, value, ratio
                    )
                }
            )
            CustomSpacer(width = 24.dp)
            Dropdown(
                destExpanded,
                destValue,
                onExpand = {
                    destExpanded = it
                },
                onValueChange = { value: String, ratio: Double ->
                    onValueChanged(
                        false, value, ratio
                    )
                }
            )
        }
        CustomSpacer(height = 24.dp)
        Text(
            text = "Result : $outputValue $destValue",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun Dropdown(
    expanded: Boolean,
    value: String,
    onExpand: (expanded: Boolean) -> Unit,
    onValueChange: (value: String, ratio: Double) -> Unit
) {
    var ddValue by remember { mutableStateOf(value) }

    fun onItemSelect(value: String, ratio: Double) {
        ddValue = value
        onExpand(false)
        onValueChange(value, ratio)
    }

    Box {
        Button(
            onClick = { onExpand(true) }
        ) {
            Text(
                text = ddValue,
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(Icons.Rounded.KeyboardArrowDown, "")
        }
        DropdownMenu(expanded, {
            onExpand(false)
        }) {
            DropdownMenuItem({
                Text("Kilometers")
            }, onClick = { onItemSelect("Kilometers", 1.0) })
            DropdownMenuItem({
                Text("Meters")
            }, onClick = {
                onItemSelect("Meters", 1 / 1000.0)
            })
            DropdownMenuItem({
                Text("Centimeters")
            }, onClick = {
                onItemSelect("Centimeters", 1 / 100000.0)
            })
            DropdownMenuItem({
                Text("Millimeters")
            }, onClick = {
                onItemSelect("Millimeters", 1 / 1000000.0)
            })
        }
    }
}

@Composable
fun CustomSpacer(width: Dp = 0.dp, height: Dp = 0.dp, modifier: Modifier = Modifier) {
    val isVertical = width == 0.dp
    if (isVertical)
        Spacer(modifier.height(height))
    else
        Spacer(modifier.width(width))
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
