package id.adiandrea.weather.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location")
    var location: Location = Location(),
    @SerializedName("current")
    var current: Current = Current()
)

fun Current.getHumidity(): String = "${humidity}%"

fun Current.getFeelsLike(): String = "${feelslikeC}\u00B0"