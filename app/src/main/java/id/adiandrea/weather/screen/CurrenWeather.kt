package id.adiandrea.weather.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import id.adiandrea.weather.R
import id.adiandrea.weather.ui.theme.mainTextColor
import id.adiandrea.weather.ui.theme.poppinsFamily
import id.adiandrea.weather.ui.theme.searchBackgroundColor
import id.adiandrea.weather.ui.theme.secondaryTextColor
import id.adiandrea.weather.ui.theme.thirdTextColor

@Composable
fun CurrentWeather(
    image: String,
    location: String,
    temperature: String,
    humidity: String,
    uv: String,
    feels: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, start = 50.dp, end = 50.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "weather icon",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(120.dp)
        )
        Spacer(Modifier.height(27.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = location,
                color = mainTextColor,
                fontSize = 30.sp,
                lineHeight = 45.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
            )
            Spacer(Modifier.width(11.dp))
            Image(
                painter = painterResource(R.drawable.ic_location),
                contentDescription = "location icon",
                modifier = Modifier
                    .size(21.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        ConstraintLayout {
            val (value, symbol) = createRefs()

            Text(
                text = temperature,
                color = mainTextColor,
                fontSize = 70.sp,
                lineHeight = 105.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
                modifier = Modifier
                    .constrainAs(value) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Canvas(
                modifier = Modifier
                    .size(5.dp)
                    .constrainAs(symbol) {
                        top.linkTo(value.top, margin = 18.dp)
                        start.linkTo(value.end, margin = 8.dp)
                    },
                onDraw = {
                    drawCircle(
                        color = mainTextColor,
                        radius = 8f,
                        style = Stroke(
                            width = 4f
                        )
                    )

                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(searchBackgroundColor)
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Humidity",
                    color = secondaryTextColor,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = humidity,
                    color = thirdTextColor,
                    fontSize = 15.sp,
                    lineHeight = 12.5.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(70.dp)
            ) {
                Text(
                    text = "UV",
                    color = secondaryTextColor,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = uv,
                    color = thirdTextColor,
                    fontSize = 15.sp,
                    lineHeight = 12.5.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Feels Like",
                    color = secondaryTextColor,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = feels,
                    color = thirdTextColor,
                    fontSize = 15.sp,
                    lineHeight = 12.5.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    ),
                )
            }
        }
    }
}