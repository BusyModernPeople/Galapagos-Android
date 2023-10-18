package com.busymodernpeople.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.R

@Preview
@Composable
fun MyPetProfile(
    modifier: Modifier = Modifier,
    name: String = "도랭이",
    gender: String = "M",
    date: String = "12"
) {
    Surface(
        modifier = Modifier
            .shadow(20.dp)
            .fillMaxWidth(0.5f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_test),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(140.dp)
            )
            Spacer(modifier = Modifier.height(17.dp))
            PetInfoComponent(name = "도랭이", gender = "M", date = "12")
            Spacer(modifier = Modifier.height(17.dp))
        }
    }
}

@Preview
@Composable
fun PetInfoComponent(
    name: String = "도랭이",
    gender: String = "M",
    date: String = "12"
) {
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = name,
                style = GalapagosTheme.typography.title4.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.FontGray1
            )
            Spacer(modifier = Modifier.width(4.dp))

            var genderIcon = if (gender == "M") R.drawable.ic_gender_man else R.drawable.ic_gender_woman
            Icon(
                painter = painterResource(genderIcon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "함께한지",
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
                color = GalapagosTheme.colors.FontGray1
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = date,
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.IconBlack
            )
            Text(
                text = "일",
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                color = GalapagosTheme.colors.IconBlack
            )
        }
    }
}