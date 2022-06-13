package com.app.assignment.presentation.games

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.assignment.domain.model.Response
import com.app.assignment.presentation.auth.AuthViewModel
import com.app.assignment.presentation.components.ProgressBar

@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit) {
//    when(val response = viewModel.signInState.value) {
//        is Response.Loading -> ProgressBar()
//        is Response.Success -> if (!response.data) {
//            LaunchedEffect(response.data) {
//                navigateToAuth()
//            }
//        }
//    }
    viewModel.getGames()
//    Button(onClick = { signOutCLick(viewModel) }){
//        Text(
//            text = "SIGN OUT",
//            fontSize = 18.sp
//        )
//    }
}

fun signOutCLick(viewModel: AuthViewModel) {
    viewModel.logout()
}