package com.nbs.unitconverter.prsesntation.composables.conversion

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.nbs.unitconverter.data.Conversion
import com.nbs.unitconverter.data.Utils
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun ConversionScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    modifier: Modifier = Modifier,
    save: (String, String) -> (Unit)
) {

    var toSave = remember { mutableStateOf(false) }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {

        SelectionMenu(list = list, isLandscape) { conversion ->
            selectedConversion.value = conversion
            typedValue.value = "0.0"
            toSave.value = true
        }

        selectedConversion.value?.let {
            InputBlock(conversion = it, inputText = inputText, isLandscape) { input ->
                typedValue.value = input
            }
        }
        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiplyBy = selectedConversion.value!!.multiplyBy

            val result = DecimalFormat("#.####").apply {
                roundingMode = RoundingMode.DOWN
            }.format(input * multiplyBy)

            val lbl1 =
                "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val lbl2 = "$result ${selectedConversion.value!!.convertTo}"

            if (toSave.value) {
                save(lbl1, lbl2)
                toSave.value = false
            }
            ResultBlock(lbl1 = lbl1, lbl2 = lbl2)
        }
    }
}
