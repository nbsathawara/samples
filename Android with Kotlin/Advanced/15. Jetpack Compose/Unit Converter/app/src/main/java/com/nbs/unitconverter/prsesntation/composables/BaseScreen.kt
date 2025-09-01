package com.nbs.unitconverter.prsesntation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nbs.unitconverter.prsesntation.ConverterViewModel
import com.nbs.unitconverter.prsesntation.ConverterViewModelFactory
import com.nbs.unitconverter.prsesntation.composables.conversion.ConversionScreen
import com.nbs.unitconverter.prsesntation.composables.history.HistoryScreen

@Composable
fun BaseScreen(
    converterViewModelFactory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = converterViewModelFactory)
) {

    val list = converterViewModel.getConversions()
    val savedResults = converterViewModel.savedResults.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandscape = true
            Row(
                modifier = modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ConversionScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                )
                { msg1, msg2 ->
                    converterViewModel.saveConversionResult(msg1, msg2)
                }
                Spacer(modifier = modifier.width(10.dp))
                HistoryScreen(
                    savedResults,
                    onRemoveAll = {
                        converterViewModel.deleteAllConversionResult()
                    }
                ) {
                    converterViewModel.deleteConversionResult(it)
                }
            }
        }
        else -> {
            isLandscape = false
            Column(modifier = modifier.padding(20.dp)) {
                ConversionScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                )
                { msg1, msg2 ->
                    converterViewModel.saveConversionResult(msg1, msg2)
                }
                Spacer(modifier = modifier.height(10.dp))
                HistoryScreen(
                    savedResults,
                    onRemoveAll = {
                        converterViewModel.deleteAllConversionResult()
                    }
                ) {
                    converterViewModel.deleteConversionResult(it)
                }
            }
        }
    }

}