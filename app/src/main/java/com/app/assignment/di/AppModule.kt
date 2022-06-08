package com.app.assignment.di

import android.content.Context
import com.app.assignment.domain.repository.auth.AuthRepository
import com.app.assignment.domain.repository.auth.AuthRepositoryImpl
import com.app.assignment.domain.usecase.auth.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.paperdb.Paper

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
    fun provideAuthUseCases(
        repository: AuthRepository
    ) = AuthUseCase(
        isUserAuthenticated = IsUserAuthenticated(repository),
        storeToken = StoreToken(repository)
    )
}