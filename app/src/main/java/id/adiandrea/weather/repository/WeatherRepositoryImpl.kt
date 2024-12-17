package id.adiandrea.weather.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import id.adiandrea.weather.model.ErrorResponse
import id.adiandrea.weather.model.WeatherResponse
import id.adiandrea.weather.network.APIService
import id.adiandrea.weather.network.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val preferences: DataStore<Preferences>
) : WeatherRepository {
    override fun searchLocation(query: String): Flow<BaseResponse<WeatherResponse>> {
        return flow {
            emit(BaseResponse.Loading)
            val response = apiService.searchLocation(query)
            if (response.isSuccessful) {
                emit(BaseResponse.Success(response.body()))
            } else {
                val errorBody = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                emit(BaseResponse.Error(errorBody.error.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveLocation(data: WeatherResponse) {
        preferences.edit { pref ->
            pref[stringPreferencesKey("location")] = Gson().toJson(data)
        }
    }

    override suspend fun getSavedWeatherLocation(): Flow<String?> {
        return preferences.data.map { preferences ->
            preferences[stringPreferencesKey("location")]
        }
    }

}