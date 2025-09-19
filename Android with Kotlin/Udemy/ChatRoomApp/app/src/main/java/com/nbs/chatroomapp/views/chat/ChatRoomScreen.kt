package com.nbs.chatroomapp.views.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbs.chatroomapp.R
import com.nbs.chatroomapp.data.models.Message
import com.nbs.chatroomapp.data.toChatDateTime
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
    val messageList by viewModel.messageList.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    LaunchedEffect(messageList) {
        listState.scrollToItem(messageList.size - 1)
    }

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
        if (messageList.isEmpty())
            EmptyView(
                modifier = Modifier.padding(it),
                emptyMsg = stringResource(R.string.no_messages)
            )
        else
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(top = 8.dp),
                state = listState
            ) {
                items(messageList) { item ->
                    if (item is String)
                        MessageDateItem(item)
                    else
                        MessageItem(
                            item as Message,
                            isMe = item.sender == currentUser?.firstName
                        )
                }
//                messageList.forEach { (date, messages) ->
//                    item {
//                        MessageDateItem(date = date)
//                    }
//                    items(messages) { message ->
//                        MessageItem(
//                            message = message,
//                            isMe = message.sender == currentUser?.firstName
//                        )
//                    }
//                }
            }
    }
}

@Composable
fun MessageDateItem(date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(20)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            text = date,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun MessageItem(message: Message, isMe: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = if (isMe)
            Alignment.CenterEnd
        else
            Alignment.CenterStart
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isMe)
                    MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.secondary,
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(8.dp),
            ) {
                if (!isMe) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = message.sender,
                        color = Color.White,
                        textAlign = if (isMe) TextAlign.End else TextAlign.Start,
                        style = MaterialTheme.typography.bodySmall
                    )
                    CustomSpacer(height = 4.dp)
                }
                Text(
                    text = message.text,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
                CustomSpacer(height = 4.dp)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = message.timestamp.toChatDateTime(),
                    color = Color.LightGray,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
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
    //SendMessage({})

//    MessageItem(
//        Message(
//            text = "Hello World!!!!",
//            sender = "Nikhil",
//            timestamp = System.currentTimeMillis()
//        )
//    )

    ChatRoomScreen("", "")
}