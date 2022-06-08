package com.app.assignment.presentation.auth

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.assignment.domain.model.Response
import com.app.assignment.presentation.components.ProgressBar

@Composable
fun AuthScreen (
    viewModel: AuthViewModel = hiltViewModel(),
    onSignIn: () -> Unit,
    navigateToGameScreen: () -> Unit
){

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                AuthContent(onSignIn)
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
        is Error -> LaunchedEffect(Unit) {
            response.message?.let { Log.d("Error", it) }
        }
    }
}