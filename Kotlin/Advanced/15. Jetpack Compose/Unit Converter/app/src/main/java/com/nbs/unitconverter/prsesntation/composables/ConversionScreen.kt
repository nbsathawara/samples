package com.nbs.unitconverter.prsesntation.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.nbs.unitconverter.data.Conversion
import com.nbs.unitconverter.data.Utils
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun ConversionScreen(list: List<Conversion>) {

    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val typedValue = remember { mutableStateOf("0.0") }

    SelectionMenu(list = list) { conversion ->
        selectedConversion.value = conversion
    }

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->
            typedValue.value = input
        }


        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiplyBy = selectedConversion.value!!.multiplyBy

            val result = DecimalFormat("#.####").apply {
                roundingMode = RoundingMode.DOWN
            }.format(input * multiplyBy)

            val lbl1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val lbl2 = "$result ${selectedConversion.value!!.convertTo}"

            ResultBlock(lbl1 = lbl1, lbl2 = lbl2)
        }
    }
}