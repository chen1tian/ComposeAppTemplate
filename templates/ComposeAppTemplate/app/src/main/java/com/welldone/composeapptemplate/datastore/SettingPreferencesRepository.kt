package com.welldone.composeapptemplate.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    // 定义Key
    private object PreferencesKeys {
        val VALUE1 = stringPreferencesKey("Value1")
    }

    // 使用流读取数据
    val preferenceFlow = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            Log.e("SettingPreferencesRepository", "Error reading preferences.", exception)
        }
    }.map { preferences ->
        val value1 = preferences[PreferencesKeys.VALUE1] ?: ""

        SettingPreferences(
            value1 = value1
        )
    }

    /**
     * 更新Valu1
     */
    suspend fun updateValue1(value1: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.VALUE1] = value1
        }
    }
}