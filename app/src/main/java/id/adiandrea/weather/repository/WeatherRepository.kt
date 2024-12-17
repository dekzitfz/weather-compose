package id.adiandrea.weather.repository

import id.adiandrea.weather.model.WeatherResponse
import id.adiandrea.weather.network.BaseResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun searchLocation(query: String): Flow<BaseResponse<WeatherResponse>>
    suspend fun saveLocation(data: WeatherResponse)
    suspend fun getSavedWeatherLocation(): Flow<String?>
}