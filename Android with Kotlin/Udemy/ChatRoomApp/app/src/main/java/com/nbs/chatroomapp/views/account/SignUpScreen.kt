package com.nbs.chatroomapp.views.account

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbs.chatroomapp.R
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.viewmodels.account.AuthViewModel
import com.nbs.chatroomapp.views.custom.AppBar
import com.nbs.subsriptionapp.custom.CustomProgressbar
import com.nbs.subsriptionapp.custom.CustomSnackbar
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.custom.ErrorText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel,
    navigateToSingIn: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }

    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()
    val signUpResult by viewModel.authResult.collectAsStateWithLifecycle()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }
    var invalidEmail by remember { mutableStateOf(false) }
    var inValidPassword by remember { mutableStateOf(false) }
    var invalidFirstName by remember { mutableStateOf(false) }
    var invalidLastName by remember { mutableStateOf(false) }

    fun signUp() {
        firstName = firstName.trim()
        lastName = lastName.trim()
        email = email.trim()
        password = password.trim()

        invalidFirstName = firstName.isEmpty()
        invalidLastName = lastName.isEmpty()
        invalidEmail =
            email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        inValidPassword = password.isEmpty()

        if (invalidFirstName || invalidLastName || invalidEmail || inValidPassword)
            return

        keyboardController?.hide()
        viewModel.signUp(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName
        )
    }

    LaunchedEffect(signUpResult) {
        when (signUpResult) {
            HttpResult.Success(true) -> {
                snackbarHostState.showSnackbar(
                    message = "Registration successful."
                )
                delay(1000)
                onSignUpSuccess()
                email = ""
                password = ""
            }

            is HttpResult.Error -> {
                snackbarHostState.showSnackbar(
                    message = (signUpResult as HttpResult.Error).exception.message
                        ?: "Unknown error",
                )
            }

            else -> {
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.sign_up),
                navIcon = {},
                actionIcons = {}
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    CustomSnackbar(it)
                })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomSpacer(height = 8.dp)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                label = {
                    Text(text = stringResource(id = R.string.first_name))
                },
                isError = invalidFirstName,
                supportingText = {
                    if (invalidFirstName)
                        ErrorText()
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            CustomSpacer(height = 8.dp)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                label = {
                    Text(text = stringResource(id = R.string.last_name))
                },
                isError = invalidLastName,
                supportingText = {
                    if (invalidLastName)
                        ErrorText()
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            CustomSpacer(height = 8.dp)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                isError = invalidEmail,
                supportingText = {
                    if (invalidEmail)
                        ErrorText()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            CustomSpacer(height = 8.dp)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                isError = inValidPassword,
                supportingText = {
                    if (inValidPassword)
                        ErrorText()
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        signUp()
                    }
                ),
                visualTransformation = if (showPassword)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            showPassword = !showPassword
                        }) {
                        if (showPassword)
                            Icon(
                                painter = painterResource(id = R.drawable.outline_visibility_24),
                                contentDescription = ""
                            )
                        else
                            Icon(
                                painter = painterResource(id = R.drawable.outline_visibility_off_24),
                                contentDescription = ""
                            )
                    }
                }
            )
            CustomSpacer(height = 16.dp)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    signUp()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            CustomSpacer(height = 16.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_an_account),
                    style = MaterialTheme.typography.bodyMedium
                )
                CustomSpacer(width = 8.dp)
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navigateToSingIn()
                    }
                )
            }
        }
        if (isLoading.value) {
            CustomProgressbar()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignUpScreenPreview() {
    //SignUpScreen()
}