package com.app.assignment.domain.usecase.games

import com.app.assignment.domain.repository.games.GamesRepository


class GetGames(
    private val repository: GamesRepository
) {
    suspend operator fun invoke() = repository.getGames()
}