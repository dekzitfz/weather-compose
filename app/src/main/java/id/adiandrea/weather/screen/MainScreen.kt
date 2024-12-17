package id.adiandrea.weather.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import id.adiandrea.weather.WeatherViewModel
import id.adiandrea.weather.model.getFeelsLike
import id.adiandrea.weather.model.getHumidity
import id.adiandrea.weather.model.getWeatherImage
import id.adiandrea.weather.network.BaseResponse
import id.adiandrea.weather.ui.theme.WeatherTheme
import id.adiandrea.weather.ui.theme.poppinsFamily
import id.adiandrea.weather.ui.theme.searchBackgroundColor
import id.adiandrea.weather.ui.theme.searchTextColor
import id.adiandrea.weather.ui.theme.searchTrailingIconColor

@Preview
@Composable
fun MainScreen(
    viewmodel: WeatherViewModel = hiltViewModel()
) {
    WeatherTheme {
        Scaffold { innerPadding ->
            ConstraintLayout(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(color = Color.White)
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, top = 44.dp, bottom = 44.dp)
            ) {
                LaunchedEffect(Unit) {
                    viewmodel.getCurrentWeather()
                }

                val (search, content) = createRefs()

                var isSearching by remember { mutableStateOf(false) }
                var searchValue by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(search) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    value = searchValue,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            Log.d("MainActivity", "Search keyword: $searchValue")
                            keyboardController?.hide()
                            viewmodel.fetchWeather(searchValue)
                        }
                    ),
                    textStyle = TextStyle(
                        color = searchTextColor,
                        fontSize = 15.sp,
                        lineHeight = 22.5.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search"
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    placeholder = {
                        Text(
                            text = "Search Location",
                            color = searchTrailingIconColor,
                            fontSize = 15.sp,
                            lineHeight = 22.5.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = searchTextColor,
                        focusedContainerColor = searchBackgroundColor,
                        unfocusedContainerColor = searchBackgroundColor,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        focusedTrailingIconColor = searchTrailingIconColor,
                        unfocusedTrailingIconColor = searchTrailingIconColor
                    ),
                    onValueChange = { value ->
                        if(value.isEmpty()){
                            viewmodel.getCurrentWeather()
                        }else{
                            viewmodel.clearResult()
                        }
                        isSearching = value.isNotEmpty()
                        searchValue = value
                    }
                )

                when(val weatherResponse = viewmodel.weatherResult.value) {
                    is BaseResponse.Loading -> {
                        Log.d("MainScreen", "LOADING IN PROGRESS")
                        Box(
                            modifier = Modifier
                                .constrainAs(content) {
                                    top.linkTo(search.bottom, margin = 20.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.width(64.dp),
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }

                    }
                    is BaseResponse.Error -> {
                        Log.w("MainScreen", weatherResponse.message)
                        Toast.makeText(LocalContext.current, weatherResponse.message, Toast.LENGTH_SHORT).show()
                    }
                    is BaseResponse.Success -> {
                        val weatherData = weatherResponse.data
                        if (weatherData == null) {
                            if (!isSearching) {
                                Box(
                                    modifier = Modifier
                                        .constrainAs(content) {
                                            top.linkTo(search.bottom)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                            bottom.linkTo(parent.bottom)
                                        }
                                ) {
                                    EmptyScreen()
                                }
                            }
                        } else {
                            Log.d("MainScreen", "Should showing Weather data: $weatherData")
                            Box(
                                modifier = Modifier
                                    .constrainAs(content) {
                                        top.linkTo(search.bottom, margin = 20.dp)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        if (!isSearching) bottom.linkTo(parent.bottom)
                                    }
                            ) {
                                if (isSearching) {
                                    SearchResult(
                                        onSelected = {
                                            viewmodel.saveWeatherLocation(weatherData)
                                            isSearching = false
                                            searchValue = ""
                                            viewmodel.getCurrentWeather()
                                        },
                                        image = weatherData.current.condition.getWeatherImage(),
                                        location = weatherData.location.name,
                                        temperature = weatherData.current.tempC.toString()
                                    )
                                } else {
                                    CurrentWeather(
                                        image = weatherData.current.condition.getWeatherImage(),
                                        location = weatherData.location.name,
                                        temperature = weatherData.current.tempC.toString(),
                                        humidity = weatherData.current.getHumidity(),
                                        uv = weatherData.current.uv.toString(),
                                        feels = weatherData.current.getFeelsLike(),
                                    )
                                }

                            }
                        }
                    }
                }

            }
        }
    }
}