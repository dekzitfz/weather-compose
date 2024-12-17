package id.adiandrea.weather.model


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text")
    var text: String = "",
    @SerializedName("icon")
    var icon: String = "",
    @SerializedName("code")
    var code: Int = 0
)

fun Condition.getWeatherImage(): String {
    // //cdn.weatherapi.com/weather/64x64/night/116.png
    return "https:${icon.replace("64x64", "128x128")}"
}