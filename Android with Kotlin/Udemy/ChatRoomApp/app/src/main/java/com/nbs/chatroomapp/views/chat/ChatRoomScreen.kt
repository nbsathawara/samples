package com.nbs.chatroomapp.views.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbs.chatroomapp.R
import com.nbs.chatroomapp.data.models.Message
import com.nbs.chatroomapp.data.toDateTime
import com.nbs.chatroomapp.viewmodels.chat.ChatroomViewModel
import com.nbs.chatroomapp.views.custom.AppBar
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.custom.EmptyView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomScreen(
    id: String = "",
    title: String = "",
    viewModel: ChatroomViewModel = hiltViewModel()
) {
    viewModel.setRoomId(id)
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()

    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(
                title = title,
                navIcon = {},
                actionIcons = {}
            )
        },
        bottomBar = {
            SendMessage(onMessageSent = { message ->
                viewModel.sendMessage(message)
            })
        }
    ) {
        if (messages.isEmpty())
            EmptyView(
                modifier = Modifier.padding(it),
                emptyMsg = stringResource(R.string.no_messages)
            )
        else
            PullToRefreshBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = pullToRefreshState,
                isRefreshing = isRefreshing,
                onRefresh = {
                    viewModel.fetchMessages()
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(messages) { message ->
                        MessageItem(
                            message = message,
                            isMe = message.sender == currentUser?.firstName
                        )
                    }
                }
            }
    }
}

@Composable
fun MessageItem(message: Message, isMe: Boolean = false) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isMe) Color.Blue else Color.Gray,
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = message.text,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        CustomSpacer(height = 3.dp)
        Text(
            text = message.sender,
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )
        CustomSpacer(height = 3.dp)
        Text(
            text = message.timestamp.toDateTime(),
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun SendMessage(
    onMessageSent: (String) -> Unit
) {

    var message by remember { mutableStateOf("") }

    fun sendMessage() {
        if (message.isEmpty())
            return
        onMessageSent(message)
        message = ""
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
               horizontal = 8.dp, vertical = 24.dp
            )
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
            },
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(text = stringResource(R.string.type_message))
            },
            trailingIcon = {
                IconButton(
                    enabled = message.isNotEmpty(),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Blue,
                        disabledContentColor = Color.LightGray
                    ),
                    onClick = {
                        sendMessage()
                    }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = ""
                    )
                }
            }

        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SendMessage({})
//    MessageItem(
//        Message(
//            "12",
//            "Sample Message",
//            "Nikhil Sathawara", 1234567890
//        )
//    )

    //ChatRoomScreen("", "")
}