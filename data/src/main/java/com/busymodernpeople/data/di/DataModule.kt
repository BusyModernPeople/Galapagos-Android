package com.busymodernpeople.data.di

import com.busymodernpeople.core.network.service.AuthService
import com.busymodernpeople.data.datasource.AuthDataSource
import com.busymodernpeople.data.datasource.AuthDataSourceImpl
import com.busymodernpeople.data.repository.AuthRepository
import com.busymodernpeople.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesAuthDataSource(
        authService: AuthService
    ): AuthDataSource =
        AuthDataSourceImpl(authService)

    @Provides
    @Singleton
    fun providesAuthRepository(
        authDataSource: AuthDataSource
    ): AuthRepository =
        AuthRepositoryImpl(authDataSource)

}