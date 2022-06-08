package com.app.assignment.domain.repository.games

import com.app.assignment.domain.model.Game
import com.app.assignment.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getGames(): Flow<Response<List<Game>>>
}