package com.app.assignment.presentation.games

import com.app.assignment.domain.model.Game

data class GamesState(
    val games: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val errorCode: Int? = null
)