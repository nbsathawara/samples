package com.nbs.locationapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nbs.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    val locationViewModel: LocationViewModel = viewModel()
    LocationDisplay(context, locationViewModel, PermissionUtils(), LocationUtils())
}

@Composable
fun LocationDisplay(
    context: Context,
    viewModel: LocationViewModel,
    permissionUtils: PermissionUtils,
    locationUtils: LocationUtils
) {

    val address = viewModel.locationData.value?.let {
        locationUtils.reverseGeocodeLocation(context, viewModel.locationData.value!!)
    }

    fun requestLocationUpdates() {
        viewModel.updateMsg("Getting Location...")
        locationUtils.requestLocationUpdates(context, viewModel)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true
                && permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {
                requestLocationUpdates()
            } else {
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            context as MainActivity,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        )

                if (rationalRequired) {
                    Toast.makeText(
                        context,
                        "Location required for this feature to work!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Location required for this feature to work." +
                                "So Please enable location from settings!!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(viewModel.msg.value + "\n" + (address ?: ""))
        Button({
            if (permissionUtils.hasLocationPermission(context)) {
                requestLocationUpdates()
            } else {
                requestPermissionLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        }) {
            Text("Get Location")
        }
    }
}