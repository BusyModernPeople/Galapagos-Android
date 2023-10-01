package com.busymodernpeople.data.repository

import com.busymodernpeople.data.datasource.DataStoreDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {

    override fun getFcmToken(): Flow<String> {
        return dataStoreDataSource.getStringValue(FCM_TOKEN)
    }

    override suspend fun setFcmToken(token: String) {
        dataStoreDataSource.setStringValue(FCM_TOKEN, token)
    }

    override fun getJwtToken(): Flow<String> {
        return dataStoreDataSource.getStringValue(JWT_TOKEN)
    }

    override suspend fun setJwtToken(token: String) {
        dataStoreDataSource.setStringValue(JWT_TOKEN, token)
    }

}