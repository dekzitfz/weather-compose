package id.adiandrea.weather.network

sealed class BaseResponse<out T> {
    object Loading: BaseResponse<Nothing>()
    data class Success<out T>(val data: T?): BaseResponse<T>()
    data class Error(val message: String): BaseResponse<Nothing>()
}