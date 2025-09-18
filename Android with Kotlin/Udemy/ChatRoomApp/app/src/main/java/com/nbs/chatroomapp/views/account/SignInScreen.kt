package com.nbs.chatroomapp.views.account

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun SignInScreen(
    viewModel: AuthViewModel,
    navigateToSignUp: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()
    val signInResult by viewModel.authResult.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf("nbsathawara@gmail.com") }
    var password by remember { mutableStateOf("123456") }

    var showPassword by remember { mutableStateOf(false) }
    var invalidEmail by remember { mutableStateOf(false) }
    var inValidPassword by remember { mutableStateOf(false) }

    fun signIn() {

        email = email.trim()
        password = password.trim()

        invalidEmail =
            email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        inValidPassword = password.isEmpty()

        if (invalidEmail || inValidPassword)
            return

        viewModel.signIn(
            email = email,
            password = password
        )
    }

    LaunchedEffect(signInResult) {
        when (signInResult) {
            HttpResult.Success(true) -> {
                snackbarHostState.showSnackbar(
                    message = "Sign in successful"
                )
                delay(500)
                onSignInSuccess()
                email = ""
                password = ""
            }

            is HttpResult.Error -> {
                    snackbarHostState.showSnackbar(
                        message = (signInResult as HttpResult.Error).exception.message
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
                title = stringResource(id = R.string.sign_in),
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
                    keyboardType = KeyboardType.Email
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
                    signIn()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
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
                    text = stringResource(id = R.string.dont_have_an_account),
                    style = MaterialTheme.typography.bodyMedium
                )
                CustomSpacer(width = 8.dp)
                Text(
                    text = stringResource(id = R.string.sign_up),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navigateToSignUp()
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
fun SignInScreenPreview() {
//    SignInScreen(
//        navigateToSignUp = {},
//        onSignInSuccess = {}
//    )
}