package com.app.assignment.domain.repository.games

import com.app.assignment.domain.model.Game
import com.app.assignment.domain.model.Response
import com.app.assignment.domain.remotesource.GamesService
import io.paperdb.Paper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl
@Inject constructor(private val gamesService: GamesService) : GamesRepository {
    override suspend fun getGames() = flow {
        try {
            val token = Paper.book().read<String>("auth")
            val gamesList: List<Game>? = token?.let {
                gamesService.gamesList(it)
            }
            gamesList?.let {
                emit(Response.Success(gamesList))
            } ?: emit(Response.Error("Something went wrong"))
        }catch (e: Exception){
            emit(Response.Error(e.message!!))
        }

    }
}