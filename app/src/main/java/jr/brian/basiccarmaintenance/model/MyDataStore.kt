package jr.brian.basiccarmaintenance.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore:
                DataStore<Preferences> by preferencesDataStore("my-data-store")
        val CURRENT_SELECTED_VEHICLE = stringPreferencesKey("CURRENT-VEHICLE")
        val DAILY_AVERAGE_MILEAGE = floatPreferencesKey("AVERAGE-MILEAGE")
    }

    val getCurrentVehicle: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[CURRENT_SELECTED_VEHICLE]
    }

    suspend fun saveCurrentVehicle(currentVehicle: String) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_SELECTED_VEHICLE] = currentVehicle
        }
    }

    val getDailyMileageAverage: Flow<Float?> = context.dataStore.data.map { preferences ->
        preferences[DAILY_AVERAGE_MILEAGE]
    }

    suspend fun saveDailyMileageAverage(avg: Float) {
        context.dataStore.edit { preferences ->
            preferences[DAILY_AVERAGE_MILEAGE] = avg
        }
    }
}