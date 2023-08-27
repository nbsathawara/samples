package com.nbs.unitconverter.prsesntation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nbs.unitconverter.data.Conversion
import com.nbs.unitconverter.data.ConversionResultRepository
import com.nbs.unitconverter.database.ConversionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

class ConverterViewModel(private val conversionResultRepository: ConversionResultRepository) :
    ViewModel() {

    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val inputText: MutableState<String> = mutableStateOf("")
    val typedValue = mutableStateOf("0.0")

    fun getConversions() = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers to Miles", "km", "mi", 0.621371)
    )


    val savedResults = conversionResultRepository.getAllConversionResult()

    fun saveConversionResult(msg1: String, msg2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            conversionResultRepository.insertConversionResult(ConversionResult(0, msg1, msg2))
        }
    }

    fun deleteConversionResult(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            conversionResultRepository.deleteConversionResult(conversionResult)
        }
    }

    fun deleteAllConversionResult() {
        viewModelScope.launch(Dispatchers.IO) {
            conversionResultRepository.deleteAll()
        }
    }
}

class ConverterViewModelFactory @Inject constructor(private val conversionResultRepository: ConversionResultRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConverterViewModel(conversionResultRepository) as T
    }
}
