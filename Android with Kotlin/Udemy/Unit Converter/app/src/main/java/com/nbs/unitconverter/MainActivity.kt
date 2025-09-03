package com.nbs.unitconverter

import android.content.res.Resources
import android.os.Bundle
import android.text.Layout
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nbs.unitconverter.ui.theme.UnitConverterTheme

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
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter"
        )
        CustomSpacer(height = 96.dp)
        OutlinedTextField(
            value = "",
            //placeholder = Text("Enter unit here..."),
            onValueChange = {

            }
        )
        CustomSpacer(height = 24.dp)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Dropdown()
            CustomSpacer(width = 24.dp)
            Dropdown()
        }
        CustomSpacer(height = 24.dp)
        Text(text = "Result")
    }
}

@Composable
fun Dropdown() {
    Box {
        Button(
            onClick = {

            }
        ) {
            Text(text = "Select")
            Icon(Icons.Rounded.KeyboardArrowDown, "")
        }
        DropdownMenu(true, {

        }) {
            DropdownMenuItem({
                Text("Centimeters")
            }, onClick = {})
            DropdownMenuItem({
                Text("Meters")
            }, onClick = {})
            DropdownMenuItem({
                Text("Millimeters")
            }, onClick = {})
            DropdownMenuItem({
                Text("Feet")
            }, onClick = {})
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
