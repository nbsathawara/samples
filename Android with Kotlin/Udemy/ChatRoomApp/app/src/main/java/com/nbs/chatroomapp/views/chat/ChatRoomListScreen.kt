package com.nbs.chatroomapp.views.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbs.chatroomapp.R
import com.nbs.chatroomapp.data.models.ChatRoom
import com.nbs.chatroomapp.data.toLowerUnderScore
import com.nbs.chatroomapp.viewmodels.chat.ChatRoomListViewModel
import com.nbs.chatroomapp.views.custom.AppBar
import com.nbs.subsriptionapp.custom.CustomProgressbar
import com.nbs.subsriptionapp.custom.CustomSnackbar
import com.nbs.subsriptionapp.custom.EmptyView
import com.nbs.subsriptionapp.custom.NavigationIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomListScreen(
    viewModel: ChatRoomListViewModel = hiltViewModel(),
    onChatRoomJoined: (ChatRoom) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    var showCreateRoomDialog by remember { mutableStateOf(false) }

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val chatRooms by viewModel.chatRooms.collectAsStateWithLifecycle()
    val msg by viewModel.message.collectAsStateWithLifecycle()

    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    LaunchedEffect(msg) {
        if (msg.isNotEmpty()) {
            snackbarHostState.showSnackbar(message = msg, duration = SnackbarDuration.Short)
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.chat_rooms),
                navIcon = {},
                actionIcons = {
                    NavigationIcon(
                        icon = Icons.Default.Add,
                        onClick = {
                            showCreateRoomDialog = true
                        }
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    CustomSnackbar(it)
                })
        },
        containerColor = Color.LightGray
    ) {
        if (chatRooms.isEmpty())
            EmptyView(
                modifier = Modifier.padding(it),
                emptyMsg = stringResource(id = R.string.no_chat_rooms)
            )
        else
            PullToRefreshBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = pullToRefreshState,
                isRefreshing = isRefreshing,
                onRefresh = {
                    viewModel.fetchChatRooms()
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(chatRooms) { chatroom ->
                        ChatRoomItem(
                            chatRoom = chatroom,
                            onChatRoomJoined = {
                                onChatRoomJoined(chatroom)
                            })
                    }
                }
            }

        if (isLoading) {
            CustomProgressbar()
        }

        if (showCreateRoomDialog) {
            NewChatRoomScreen(
                onChatRoomCreated = { it ->
                    val chatRoom = ChatRoom(
                        id = "cr_" + it.toLowerUnderScore(),
                        name = it,
                        createdBy = viewModel.currentUser.value?.email ?: "Unknown User"
                    )
                    viewModel.createChatRoom(chatRoom)
                    showCreateRoomDialog = false
                },
                onDismiss = {
                    showCreateRoomDialog = false
                }
            )
        }
    }
}

@Composable
fun ChatRoomItem(chatRoom: ChatRoom, onChatRoomJoined: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = chatRoom.name,
                style = MaterialTheme.typography.titleMedium
            )
            Button(
                {
                    onChatRoomJoined()
                }
            ) {
                Text(
                    text = stringResource(R.string.join),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomPreview() {
//    ChatRoomItem(
//        ChatRoom(
//            id = "1",
//            name = "Test Room",
//            createdBy = "Test User"
//        )
//    )
}