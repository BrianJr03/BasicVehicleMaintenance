package jr.brian.basiccarmaintenance.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore:
                DataStore<Preferences> by preferencesDataStore("my-data-store")
        val CURRENT_SELECTED_VEHICLE = stringPreferencesKey("CURRENT-VEHICLE")
    }

    val getCurrentVehicle: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[CURRENT_SELECTED_VEHICLE]
    }

    suspend fun saveCurrentVehicleIndex(currentVehicle: String) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_SELECTED_VEHICLE] = currentVehicle
        }
    }
}