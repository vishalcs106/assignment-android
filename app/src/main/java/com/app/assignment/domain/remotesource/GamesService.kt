package com.app.assignment.domain.remotesource

import com.app.assignment.domain.model.Game
import com.app.assignment.domain.model.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GamesService {
    @GET("api/games")
    suspend fun gamesList(@Header("Authorization") authToken: String): List<Game>
}