package com.nbs.weatherapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.weatherapp.models.WeatherInfo
import com.nbs.weatherapp.views.custom.CustomSpacer


@Composable
fun WeatherInfo(weatherInfo: WeatherInfo) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = "Location Icon",
                    modifier = Modifier.size(24.dp)
                )
                CustomSpacer(width = 8.dp)
                Text(
                    text = weatherInfo.location,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            CustomSpacer(height = 8.dp)
            Text(
                text = "Temperature: ${weatherInfo.temperature}°C",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            CustomSpacer(height = 8.dp)
            Text(
                text = "Description: ${weatherInfo.description}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview()
@Composable
fun WeatherInfoPreview() {
    WeatherInfo(
        weatherInfo = WeatherInfo(
            location = "New York",
            temperature = 25,
            description = "Sunny"
        )

    )
}