package com.app.assignment.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.app.assignment.domain.model.Response
import com.app.assignment.domain.usecase.auth.AuthUseCase
import com.app.assignment.presentation.navigation.Screen
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.OnCompleteListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: AuthUseCase
    ): ViewModel() {

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    val isUserAuthenticated get() = useCases.isUserAuthenticated()

    fun storeToken(token: String) {
        useCases.storeToken.execute(token)
    }

    fun logout(oneTapClient: SignInClient) {
        viewModelScope.launch {
            oneTapClient.signOut().addOnSuccessListener {
                _signInState.value = Response.Success(false)
            }.addOnFailureListener(){
                System.out.println()
            }
        }
    }

}