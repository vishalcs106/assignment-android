package com.app.assignment.domain.repository.auth

import android.content.Context
import com.app.assignment.domain.model.Response
import com.app.assignment.presentation.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.paperdb.Paper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl  @Inject constructor(
    private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean {
        val user = firebaseAuth.currentUser
        return user != null
    }

    override fun storeToken(token: String) {
        Paper.book().write("token", token)
    }

    override suspend fun loginToFirebase(token: String) = flow {
        try {
            val credential = GoogleAuthProvider.getCredential(token, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            if (result.user != null) {
                emit(Response.Success(true))
            } else {
                emit(Response.Error("Unable to login"))
            }
        }catch (e: Exception){
            emit(Response.Error(e.message ?: "Unable to login - Unknown error"))
        }
    }
}