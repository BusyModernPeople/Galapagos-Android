package com.busymodernpeople.feature.mypage

import androidx.compose.foundation.Image
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
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun PetDetailScreen(

) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(leadingIconOnClick = { /*TODO*/ }, content = "도랭이", trailingIconOnClick = { /* TODO */ }, trailingIcon = R.drawable.ic_write)
        Spacer(modifier = Modifier.height(40.dp))
        Surface(
            modifier = Modifier.size(110.dp),
            shape = RoundedCornerShape(13.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_animal_category),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
        PetDetailDataComponent(name = "도랭이")
        PetDetailDataComponent(gender = "M")
        PetDetailDataComponent(animalType = "레오파드 게코")
        PetDetailDataComponent(getDate = "2000-03-05")
        PetDetailDataComponent(birthDate = "2000-02-04")
    }
}

@Preview
@Composable
fun PetDetailDataComponent(
    name: String? = null,
    gender: String? = null,
    animalType: String? = null,
    getDate: String? = null,
    birthDate: String? = null
) {
    var title = ""
    var data = ""

    if (name != null) { data = name
        title = "이름" }
    else if (gender != null) { data = gender
        title = "성별" }
    else if (animalType != null) { data = animalType
        title = "종" }
    else if (getDate != null) { data = getDate
        title = "입양일" }
    else if (birthDate != null) { data = birthDate
        title = "탄생일" }

    Column(
        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = GalapagosTheme.typography.body2,
                color = Color(0xFF515151)
            )

            if (title == "이름") {
                Row(
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_green_check),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "대표동물 설정",
                        style = GalapagosTheme.typography.body2,
                        color = GalapagosTheme.colors.FontBlack
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data,
                style = GalapagosTheme.typography.title4,
                color = GalapagosTheme.colors.IconBlack
            )
            Spacer(modifier = Modifier.width(4.dp))

            if (title == "성별") {
                var genderIcon = if (gender == "M") R.drawable.ic_gender_man else R.drawable.ic_gender_woman
                Icon(
                    painter = painterResource(id = genderIcon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
    }
}