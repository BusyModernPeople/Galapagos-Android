package com.busymodernpeople.data.repository

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    val FCM_TOKEN: Preferences.Key<String>
        get() = stringPreferencesKey("fcmToken")
    val JWT_TOKEN: Preferences.Key<String>
        get() = stringPreferencesKey("jwtToken")

    fun getFcmToken(): Flow<String>
    suspend fun setFcmToken(token: String)

    fun getJwtToken(): Flow<String>
    suspend fun setJwtToken(token: String)

}