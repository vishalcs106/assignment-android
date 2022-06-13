package com.app.assignment.presentation.auth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.assignment.R
import com.app.assignment.domain.model.Response
import com.app.assignment.presentation.components.ProgressBar
import com.google.android.gms.common.api.ApiException

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthScreen (
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToGameScreen: () -> Unit
){

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                AuthView(null, viewModel)
            }
        }
    )

    when(val response = viewModel.signInState.value) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> if (response.data) {
            LaunchedEffect(response.data) {
                navigateToGameScreen()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AuthView(
    errorText: String?,
    viewModel: AuthViewModel
) {
    var isLoading by remember { mutableStateOf(false) }
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)

                account?.idToken?.let { token ->
                    viewModel.loginToFirebase(token)
                }
            } catch (e: ApiException) {
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInButton(
            text = "Sign in with Google",
            loadingText = "Signing in...",
            isLoading = isLoading,
            icon = painterResource(id = R.drawable.ic_google_logo),
            onClick = {
                isLoading = true
                authResultLauncher.launch(1)
            }
        )

        errorText?.let {
            isLoading = false
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = it)
        }
    }
}