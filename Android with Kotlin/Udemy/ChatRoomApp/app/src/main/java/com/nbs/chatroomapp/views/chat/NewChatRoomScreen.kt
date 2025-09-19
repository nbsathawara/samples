package com.nbs.chatroomapp.views.chat

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.nbs.chatroomapp.R
import com.nbs.subsriptionapp.custom.ErrorText

@Composable
fun NewChatRoomScreen(
    onChatRoomCreated: (name: String) -> Unit,
    onDismiss: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var invalidName by remember { mutableStateOf(false) }

    fun createChatRoom() {
        name = name.trim()
        invalidName = name.isEmpty()
        if (invalidName)
            return

        onChatRoomCreated(name)
        onDismiss()
    }

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = stringResource(id = R.string.create_chat_room))
        },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = stringResource(id = R.string.chat_room_name))
                },
                isError = invalidName,
                supportingText = {
                    if (invalidName)
                        ErrorText()
                }
            )
        },
        confirmButton = {
            Button({
                createChatRoom()
            }) {
                Text(text = stringResource(id = R.string.create))
            }
        },
        dismissButton = {
            Button({
                onDismiss()
            }) {
                Text(
                    text = stringResource(
                        id = R.string.cancel
                    )
                )
            }
        }
    )
}