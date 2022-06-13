package com.app.assignment.domain.repository.auth

import com.app.assignment.domain.model.Response
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

    override suspend fun loginToFirebase(token: String) = flow {
        try {
            val credential = GoogleAuthProvider.getCredential(token, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            if (result.user != null) {
                storeAuthToken()
                emit(Response.Success(true))
            } else {
                emit(Response.Error("Unable to login"))
            }
        }catch (e: Exception){
            emit(Response.Error(e.message ?: "Unable to login - Unknown error"))
        }
    }

    private fun storeAuthToken(){
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(true)?.addOnSuccessListener { result ->
            result.token?.let { Paper.book().write("auth", it) }
        }

    }

    override suspend fun logoutFromFirebase()= flow {
        try{
            firebaseAuth.signOut()
            emit(Response.Success(false))
        }catch (e: Exception){
            emit(Response.Error("Something went wrong"))
        }
    }

    override fun getFirebaseUser(): FirebaseUser? = firebaseAuth.currentUser
}