package com.busymodernpeople.data.datasource

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {

    fun getStringValue(type: Preferences.Key<String>): Flow<String>
    suspend fun setStringValue(type: Preferences.Key<String>, value: String)

    fun getBooleanValue(type: Preferences.Key<Boolean>): Flow<Boolean>
    suspend fun setBooleanValue(type: Preferences.Key<Boolean>, value: Boolean)

}