package com.app.assignment.presentation.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.assignment.domain.model.Response
import com.app.assignment.domain.usecase.auth.AuthUseCase
import com.app.assignment.domain.usecase.games.GamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val useCases: GamesUseCase
): ViewModel() {

    var state by mutableStateOf(GamesState())

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            useCases.getGames().collect{ response ->
                when(response){
                    is Response.Success -> {
                        state = state.copy(games = response.data, isLoading = false, error = null)
                    }
                    is Response.Error -> {
                        state = state.copy(isLoading = false, error = response.message)
                    }
                    is Response.UnAuthorized -> {
                        state = state.copy(isLoading = false, errorCode = 401)
                    }
                    else -> Unit
                }
            }
        }
    }
}