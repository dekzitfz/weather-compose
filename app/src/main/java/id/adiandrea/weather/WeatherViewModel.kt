package id.adiandrea.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import id.adiandrea.weather.model.WeatherResponse
import id.adiandrea.weather.network.BaseResponse
import id.adiandrea.weather.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherResult = mutableStateOf<BaseResponse<WeatherResponse>>(BaseResponse.Success(null))
    val weatherResult: State<BaseResponse<WeatherResponse>> = _weatherResult

    private val _currentWeather = mutableStateOf<BaseResponse<WeatherResponse>>(BaseResponse.Success(null))
    val currentWeather: State<BaseResponse<WeatherResponse>> = _currentWeather

    fun fetchWeather(query: String) {
        viewModelScope.launch {
            repository.searchLocation(query).collect {
                _weatherResult.value = it
            }
        }
    }

    fun clearResult() {
        _weatherResult.value = BaseResponse.Success(null)
    }

    fun getCurrentWeather() {
        viewModelScope.launch {
            repository.getSavedWeatherLocation().collect { savedLocation ->
                savedLocation?.let {
                    val data = Gson().fromJson(it, WeatherResponse::class.java).location.name
                    fetchWeather(data)
                }
            }
        }
    }

    fun saveWeatherLocation(data: WeatherResponse) {
        viewModelScope.launch {
            repository.saveLocation(data)
        }

    }



}