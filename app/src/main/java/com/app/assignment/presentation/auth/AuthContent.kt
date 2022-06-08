package com.app.assignment.presentation.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.assignment.ui.theme.Teal200

@Composable
fun AuthContent(
    onSignIn: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 48.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        OutlinedButton(
            onClick = {
                onSignIn()
            },

            modifier = Modifier.border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "SignIn With Google",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}