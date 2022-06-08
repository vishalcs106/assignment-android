package com.app.assignment.presentation.games

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun GamesScreen(signOutClick: () -> Unit) {
    Button(onClick = signOutClick){
        Text(
            text = "SIGN OUT",
            fontSize = 18.sp
        )
    }
}