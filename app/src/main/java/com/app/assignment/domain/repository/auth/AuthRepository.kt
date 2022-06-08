package com.app.assignment.domain.repository.auth

import com.app.assignment.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    fun storeToken(token: String)
    suspend fun loginToFirebase(token: String): Flow<Response<Boolean>>
}