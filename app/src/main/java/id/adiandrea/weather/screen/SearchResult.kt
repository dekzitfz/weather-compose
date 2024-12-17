package id.adiandrea.weather.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import id.adiandrea.weather.ui.theme.mainTextColor
import id.adiandrea.weather.ui.theme.poppinsFamily
import id.adiandrea.weather.ui.theme.searchBackgroundColor

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun SearchResult(
    image: String = "https://cdn.weatherapi.com/weather/128x128/day/113.png",
    location: String = "Mumbai",
    temperature: String = "30",
    onSelected: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(searchBackgroundColor)
            .padding(top = 16.dp, bottom = 16.dp, start = 31.dp, end = 31.dp)
            .clickable { onSelected() }
    ) {
        Column {
            Text(
                text = location,
                color = mainTextColor,
                fontSize = 20.sp,
                lineHeight = 30.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold
            )
            ConstraintLayout {
                val (value, symbol) = createRefs()

                Text(
                    text = temperature,
                    color = mainTextColor,
                    fontSize = 60.sp,
                    lineHeight = 90.sp,
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
                            radius = 5f,
                            style = Stroke(
                                width = 3f
                            )
                        )

                    }
                )
            }

        }

        AsyncImage(
            model = image,
            contentDescription = "weather icon",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(90.dp)
        )
    }
}