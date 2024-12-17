package id.adiandrea.weather.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import id.adiandrea.weather.ui.theme.mainTextColor
import id.adiandrea.weather.ui.theme.poppinsFamily

@Preview
@Composable
fun EmptyScreen() {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        val info = createRef()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(info) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "No City Selected",
                color = mainTextColor,
                fontSize = 30.sp,
                lineHeight = 45.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Please Search For A City",
                color = mainTextColor,
                fontSize = 15.sp,
                lineHeight = 22.5.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
