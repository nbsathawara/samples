package com.nbs.unitconverter.prsesntation.composables.conversion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultBlock(lbl1: String, lbl2: String, modifier: Modifier = Modifier) {
    Card(
        elevation = 20.dp,
        modifier = modifier.padding(0.dp, 20.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Text(
                text = lbl1,
                fontSize = 28.sp
            )

            Text(
                text = lbl2,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}