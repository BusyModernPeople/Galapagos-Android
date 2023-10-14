package com.busymodernpeople.feature.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun MyPetListMenu(
    modifier: Modifier = Modifier,
    petList: ArrayList<String> = arrayListOf("전체", "도마뱀", "거북이"),
    isSelected: String = "전체"
) {
    // var isSelected by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        for (i in 0 until petList.size) {
            if (i != 0) Spacer(modifier = Modifier.width(16.dp))
            if (isSelected == petList[i]) MyPetListItem(petList[i], true)
            else MyPetListItem(petList[i])
        }
    }
}

@Preview
@Composable
fun MyPetListItem(
    petName: String = "전체",
    isSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    var textColor = if(isSelected) Color(0xFF161616) else Color(0xFFDBDBDB)

    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(13.dp))
        Box(
            modifier = Modifier.padding(horizontal = 2.dp)
        ) {
            Text(
                text = petName,
                style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                color = textColor
            )
        }

        if (isSelected) {
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                modifier = Modifier.height(3.dp),
                shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)
            ) {
                Box(
                    modifier = Modifier.background(color = GalapagosTheme.colors.FontBlack)
                ) {
                    Text(
                        text = petName,
                        style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                        color = Color.Transparent,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}