package id.adiandrea.weather.model


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    var code: Int = 0,
    @SerializedName("message")
    var message: String? = null
)