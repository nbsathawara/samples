package com.nbs.composerecyclerview.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nbs.composerecyclerview.model.TvShow


@Composable
fun TvShowImage(tvShow: TvShow) {
    Image(
        painter = painterResource(
            id = tvShow.imageId
        ), contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(100.dp)
            .height(140.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))

    )
}