package com.app.assignment.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    fun getGames(){
        viewModelScope.launch {
            useCases.getGames().collect{ response ->
                System.out.println("")
            }
        }

    }
}