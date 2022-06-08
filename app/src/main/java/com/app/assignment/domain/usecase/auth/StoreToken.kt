package com.app.assignment.domain.usecase.auth

import com.app.assignment.domain.repository.auth.AuthRepository

class StoreToken(
    private val authRepository: AuthRepository
){
    fun execute(token: String){
        authRepository.storeToken(token)
    }

}
