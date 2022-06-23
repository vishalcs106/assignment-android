package com.app.assignment.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.app.assignment.domain.model.Response
import com.app.assignment.domain.model.User
import com.app.assignment.domain.usecase.auth.AuthUseCase
import com.app.assignment.presentation.navigation.Screen
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: AuthUseCase
    ): ViewModel() {

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Initial)
    val signInState: State<Response<Boolean>> = _signInState

    fun loginToFirebase(idToken: String) {
        viewModelScope.launch {
            useCases.firebaseLoginUseCase(idToken).collect{ result ->
                delay(1000)
                _signInState.value = result
            }
        }
    }

    fun getUser(): FirebaseUser?{
        return useCases.firebaseGetUserUseCase()
    }

}