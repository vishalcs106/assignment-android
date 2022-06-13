package com.app.assignment.di

import com.app.assignment.domain.remotesource.GamesService
import com.app.assignment.domain.repository.auth.AuthRepository
import com.app.assignment.domain.repository.auth.AuthRepositoryImpl
import com.app.assignment.domain.repository.games.GameRepositoryImpl
import com.app.assignment.domain.repository.games.GamesRepository
import com.app.assignment.domain.usecase.auth.*
import com.app.assignment.domain.usecase.games.GamesUseCase
import com.app.assignment.domain.usecase.games.GetGames
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    fun gameRepository(gamesService: GamesService): GamesRepository = GameRepositoryImpl(gamesService)

    @Provides
    fun provideAuthUseCases(
        repository: AuthRepository
    ) = AuthUseCase(
        firebaseLoginUseCase = FirebaseLoginUseCase(repository),
        firebaseLogOutUseCase = FirebaseLogOutUseCase(repository),
        firebaseGetUserUseCase = FirebaseGetUserUseCase(repository)
    )

    @Provides
    fun provideGamesUseCase(
        repository: GamesRepository
    ) = GamesUseCase(getGames = GetGames(repository))

    @Provides
    fun providesRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.apply {
            logging.level = HttpLoggingInterceptor.Level.HEADERS
        }
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://assignment-be.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesGamesService(retrofit: Retrofit): GamesService{
        return retrofit.create(GamesService::class.java)
    }

}