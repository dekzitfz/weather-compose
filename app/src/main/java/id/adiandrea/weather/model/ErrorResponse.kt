package id.adiandrea.weather.model


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    var error: Error = Error()
)