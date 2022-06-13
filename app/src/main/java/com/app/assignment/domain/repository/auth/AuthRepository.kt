package com.app.assignment.domain.repository.auth

import com.app.assignment.domain.model.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginToFirebase(token: String): Flow<Response<Boolean>>
    suspend fun logoutFromFirebase(): Flow<Response<Boolean>>
    fun getFirebaseUser(): FirebaseUser?
}