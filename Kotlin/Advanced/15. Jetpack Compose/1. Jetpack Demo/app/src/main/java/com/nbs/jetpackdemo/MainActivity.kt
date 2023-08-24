package com.nbs.jetpackdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.jetpackdemo.ui.theme.JetpackDemoTheme

class MainActivity : ComponentActivity() {
    val map = HashMap<Int, List<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        map[1990] = listOf("Nikhil", "Sathawara", "Male", "31.07.1990")
        map[1991] = listOf("Hemangi", "Kadia", "Female", "30.10.1991")
        map[1997] = listOf("Yash", "Kadia", "Male", "18.06.1997")
        map[2001] = listOf("Vaishnavi", "Kadia", "Female", "07.10.2001")
        map[2003] = listOf("Devanshi", "Kadia", "Female", "25.05.2003")
        map[2004] = listOf("Jenil", "Kadia", "Male", "15.08.2004")
        map[2008] = listOf("Urvi", "Kadia", "Female", "10.04.2008")
        map[2022] = listOf("Dhriti", "Sathawara", "Female", "27.02.2022")
        setContent {
            JetpackDemoTheme {
                Column()
            }
        }
    }


    @Composable
    fun Column() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxHeight()
        )
        {
            map.keys.sorted().forEach { key ->
                Row(map[key]!!)
            }
        }
    }

    @Composable
    fun ColumnScope.Row(names: List<String>) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color.Cyan)
                .fillMaxWidth()
        )
        {
            names.forEach {
                Greeting(name = it, names.size)
            }
        }
    }


    @Composable
    fun RowScope.Greeting(name: String, count: Int) {
        Box(
            modifier = Modifier
                .background(color = Color.Blue)
                .weight(1f / count)
            //.fillMaxSize()
        ) {
            Text(
                text = name,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                softWrap = true,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(color = Color.Yellow)
                    .border(color = Color.Green, width = 2.dp)
                    .padding(5.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()

            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        JetpackDemoTheme {
            //Greeting("Nikhil")
        }
    }
}