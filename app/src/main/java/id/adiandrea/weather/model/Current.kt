package id.adiandrea.weather.model


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated_epoch")
    var lastUpdatedEpoch: Int = 0,
    @SerializedName("last_updated")
    var lastUpdated: String = "",
    @SerializedName("temp_c")
    var tempC: Double = 0.0,
    @SerializedName("temp_f")
    var tempF: Double = 0.0,
    @SerializedName("is_day")
    var isDay: Int = 0,
    @SerializedName("condition")
    var condition: Condition = Condition(),
    @SerializedName("wind_mph")
    var windMph: Double = 0.0,
    @SerializedName("wind_kph")
    var windKph: Double = 0.0,
    @SerializedName("wind_degree")
    var windDegree: Int = 0,
    @SerializedName("wind_dir")
    var windDir: String = "",
    @SerializedName("pressure_mb")
    var pressureMb: Double = 0.0,
    @SerializedName("pressure_in")
    var pressureIn: Double = 0.0,
    @SerializedName("precip_mm")
    var precipMm: Double = 0.0,
    @SerializedName("precip_in")
    var precipIn: Double = 0.0,
    @SerializedName("humidity")
    var humidity: Int = 0,
    @SerializedName("cloud")
    var cloud: Int = 0,
    @SerializedName("feelslike_c")
    var feelslikeC: Double = 0.0,
    @SerializedName("feelslike_f")
    var feelslikeF: Double = 0.0,
    @SerializedName("windchill_c")
    var windchillC: Double = 0.0,
    @SerializedName("windchill_f")
    var windchillF: Double = 0.0,
    @SerializedName("heatindex_c")
    var heatindexC: Double = 0.0,
    @SerializedName("heatindex_f")
    var heatindexF: Double = 0.0,
    @SerializedName("dewpoint_c")
    var dewpointC: Double = 0.0,
    @SerializedName("dewpoint_f")
    var dewpointF: Double = 0.0,
    @SerializedName("vis_km")
    var visKm: Double = 0.0,
    @SerializedName("vis_miles")
    var visMiles: Double = 0.0,
    @SerializedName("uv")
    var uv: Double = 0.0,
    @SerializedName("gust_mph")
    var gustMph: Double = 0.0,
    @SerializedName("gust_kph")
    var gustKph: Double = 0.0
)