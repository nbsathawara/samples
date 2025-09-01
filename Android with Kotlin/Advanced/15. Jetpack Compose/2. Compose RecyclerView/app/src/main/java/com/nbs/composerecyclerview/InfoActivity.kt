package com.nbs.composerecyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nbs.composerecyclerview.model.TvShow

class InfoActivity : ComponentActivity() {

    companion object {
        private const val keyTvShow = "tvShow"

        fun intent(context: Context, tvShow: TvShow) =
            Intent(context, InfoActivity::class.java).apply {
                putExtra(keyTvShow, tvShow)
            }
    }

    private val tvShow: TvShow by lazy {
        intent?.getSerializableExtra(keyTvShow) as TvShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvShowDetail(tvShow = tvShow)
        }
    }
}

@Composable
fun TvShowDetail(tvShow: TvShow) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.padding(10.dp)
    ) {
        Card(
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(text = tvShow.name, style = MaterialTheme.typography.h3)
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = tvShow.imageId), contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = tvShow.overview, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Release : ${tvShow.year}", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "IMDB : ${tvShow.rating}", style = MaterialTheme.typography.h5)
            }
        }
    }
}
