package id.adiandrea.weather.network

import id.adiandrea.weather.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("current.json")
    suspend fun searchLocation(
        @Query("q") query: String
    ): Response<WeatherResponse>
}