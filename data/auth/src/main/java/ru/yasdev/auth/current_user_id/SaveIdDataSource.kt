package ru.yasdev.auth.current_user_id

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class SaveIdDataSource(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

    suspend fun saveId(id: String?){
        when (id) {
            null -> {
                context.dataStore.edit { pref ->
                    pref[stringPreferencesKey("LastId")] = "null"
                }
            }

            else -> {
                context.dataStore.edit { pref ->
                    pref[stringPreferencesKey("LastId")] = id.toString()
                }
            }
        }
    }
}