package com.nbs.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nbs.unitconverter.ui.theme.UnitConverterTheme
import kotlin.random.Random

class TreasureGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CaptainGame()
                }
            }
        }
    }

    @Composable
    fun CaptainGame() {
        val treasuresFound = remember { mutableIntStateOf(0) }
        val direction = remember { mutableStateOf("North") }
        val stormOrTreasure = remember { mutableStateOf("") }

        Column {
            Text("Treasures Found : ${treasuresFound.intValue}")
            Text("Direction : ${direction.value}")
            Text(stormOrTreasure.value)
            Sail("North", treasuresFound, direction, stormOrTreasure)
            Sail("East", treasuresFound, direction, stormOrTreasure)
            Sail("West", treasuresFound, direction, stormOrTreasure)
            Sail("South", treasuresFound, direction, stormOrTreasure)
        }
    }

    @Composable
    fun Sail(
        direction: String = "North",
        treasuresFound: MutableIntState,
        directionState: MutableState<String>,
        stormOrTreasureState: MutableState<String>
    ) {
        Button({
            directionState.value = direction
            if (Random.nextBoolean()) {
                treasuresFound.intValue++
                stormOrTreasureState.value = "Treasure Found!!!!"
            } else {
                stormOrTreasureState.value = "Storm Ahead!!!"
            }
        }) {
            Text("Sail $direction!!!")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CaptainGame1() {
        CaptainGame()
    }
}