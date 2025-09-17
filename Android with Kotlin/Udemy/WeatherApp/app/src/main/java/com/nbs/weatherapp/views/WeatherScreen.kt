package com.nbs.weatherapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nbs.weatherapp.models.WeatherInfo
import com.nbs.weatherapp.models.WeatherUIState
import com.nbs.weatherapp.viewmodels.WeatherViewModel
import com.nbs.weatherapp.views.custom.CustomSpacer

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherUIState by weatherViewModel.weatherUIState.collectAsState()
    val locations by weatherViewModel.locations.collectAsState()
    val selectedLocation by weatherViewModel.selectedLocation.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        when (weatherUIState) {
            is WeatherUIState.Loading -> {
                CircularProgressIndicator()
            }

            is WeatherUIState.Success -> {
                val weatherInfo = (weatherUIState as WeatherUIState.Success).weatherInfo
                WeatherInfo(weatherInfo)
            }

            is WeatherUIState.Error -> {
                val errorMessage = (weatherUIState as WeatherUIState.Error).message
                Text(text = errorMessage)
            }
        }

        CustomSpacer(height = 8.dp)

        LocationSelector(
            locations = locations,
            selectedLocation = selectedLocation,
            onLocationSelected = { location ->
                weatherViewModel.onLocationSelected(location)
            }
        )
    }
}
