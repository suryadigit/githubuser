package com.example.projectgithub.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.prefDataStore by preferencesDataStore("setting")

class SettingPreferences(context: Context){
    private val settingDatabase = context.prefDataStore
    private val themeKEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> =
        settingDatabase.data.map { preferences ->
            preferences[themeKEY] ?: false
        }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean){
        settingDatabase.edit { preferences ->
            preferences[themeKEY] = isDarkModeActive
        }
    }

}