package com.busymodernpeople.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.busymodernpeople.data.datasource.AuthDataSource
import com.busymodernpeople.data.datasource.AuthDataSourceImpl
import com.busymodernpeople.data.datasource.DataStoreDataSource
import com.busymodernpeople.data.datasource.DataStoreDataSourceImpl
import com.busymodernpeople.data.network.service.AuthService
import com.busymodernpeople.data.repository.AuthRepository
import com.busymodernpeople.data.repository.AuthRepositoryImpl
import com.busymodernpeople.data.repository.DataStoreRepository
import com.busymodernpeople.data.repository.DataStoreRepositoryImpl
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

    @Provides
    @Singleton
    fun providesDataStoreDataSource(
        pref: DataStore<Preferences>
    ): DataStoreDataSource = DataStoreDataSourceImpl(pref)

    @Provides
    @Singleton
    fun providesDataStoreRepository(
        dataStoreDataSource: DataStoreDataSource
    ): DataStoreRepository =
        DataStoreRepositoryImpl(dataStoreDataSource)

}