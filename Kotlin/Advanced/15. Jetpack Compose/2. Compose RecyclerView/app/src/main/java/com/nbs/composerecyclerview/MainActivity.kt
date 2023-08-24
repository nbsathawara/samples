package com.nbs.composerecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.composerecyclerview.compose.TvShowListItem
import com.nbs.composerecyclerview.data.TvShowList
import com.nbs.composerecyclerview.model.TvShow
import com.nbs.composerecyclerview.ui.theme.ComposeRecyclerViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ScrollableList()
//            LazyList {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
            TvShows(selectedItem = {
                startActivity(InfoActivity.intent(this, it))
            })
        }
    }

}

@Composable
fun TvShows(selectedItem: (TvShow) -> Unit) {
    val tvShows = remember { TvShowList.tvShows }

    LazyColumn(
        Modifier.background(Color.LightGray),
        contentPadding = PaddingValues(5.dp),
    ) {
        items(tvShows) {
            TvShowListItem(it, selectedItem)
        }
    }
}


@Composable
fun LazyList(selectedItem: (String) -> (Unit)) {
    LazyColumn() {
        items(1000) {
            Text(
                text = "User $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(2.dp)
                    .clickable {
                        selectedItem("$it selected")
                    }
            )
            Divider(color = Color.Black)
        }
    }
}
