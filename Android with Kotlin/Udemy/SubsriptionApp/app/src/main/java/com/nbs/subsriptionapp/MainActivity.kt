package com.nbs.subsriptionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.nbs.subsriptionapp.ui.theme.SubsriptionAppTheme
import com.nbs.subsriptionapp.views.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubsriptionAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
   HomeScreen()
}