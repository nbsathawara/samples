package com.nbs.unitconverter.prsesntation.composables

import android.content.Context
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.unitconverter.data.Conversion

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> (Unit)
) {
    val btnBgrColor: MutableState<Color> = remember { mutableStateOf(Color.Blue) }
    val btnTxtColor: MutableState<Color> = remember { mutableStateOf(Color.White) }

    fun setBGR(isPressed: Boolean) {
        btnBgrColor.value = if (isPressed) Color.White else Color.Blue
        btnTxtColor.value = if (isPressed) Color.Blue else Color.White
    }

    fun performClick() {
        val input = inputText.value
        if (input.isEmpty())
            Toast.makeText(context, "Invalin input!!", Toast.LENGTH_SHORT).show()
        else
            calculate(input)
    }

    Column(modifier = modifier.padding(0.dp, 20.dp)) {
        Row(modifier = modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                modifier = modifier.fillMaxWidth(0.66f),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface.copy(0.3f)
                ),
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 30.sp
                )
            )
            Text(
                text = conversion.convertFrom,
                fontSize = 24.sp,
                modifier = modifier
                    .padding(10.dp, 30.dp)
                    .fillMaxWidth(0.34f)
            )
        }
        Spacer(modifier = modifier.height(20.dp))
        OutlinedButton(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                           setBGR(true)
                        }
                        MotionEvent.ACTION_MOVE -> {
                            setBGR(true)
                        }
                        MotionEvent.ACTION_UP -> {
                            setBGR(false)
                            performClick()
                        }
                    }
                    true
                },

            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = btnBgrColor.value
            ),
        ) {
            Text(
                text = "Convert",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = btnTxtColor.value
            )
        }
    }
}

