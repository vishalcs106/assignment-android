package com.app.assignment.presentation.auth

import androidx.activity.compose.rememberLauncherForActivityResult
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
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun AuthContent(
) {

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account != null) {
                    val firebaseCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(firebaseCredential).addOnSuccessListener {
                        System.out.println("")
//                        coroutineScope.launch {
//                            authViewModel.signIn(
//                                email = account.email,
//                                displayName = account.displayName,
//                            )
//                        }

                    }
                } else {

                }
            } catch (e: ApiException) {
            }
        }





    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 48.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        OutlinedButton(
            onClick = {
                authResultLauncher.launch(1)
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






