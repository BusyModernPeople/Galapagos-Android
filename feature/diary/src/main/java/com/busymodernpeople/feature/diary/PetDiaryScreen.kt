package com.busymodernpeople.feature.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.common.base.DiaryDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.capture.Capturable
import com.busymodernpeople.core.design.ui.capture.rememberCaptureController
import com.busymodernpeople.core.design.ui.component.DatePicker
import com.busymodernpeople.core.design.ui.component.GlassmorphicTab
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import kotlinx.coroutines.delay
import java.time.LocalDate

@Preview
@Composable
fun PetDiaryScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    var selectedItemIndex by remember { mutableStateOf(0) }
    var selectedDate: LocalDate? by remember { mutableStateOf(null) }

    val captureController = rememberCaptureController()
    var background: ImageBitmap by remember { mutableStateOf(ImageBitmap(1, 1)) }

    LaunchedEffect(true) {
        while (true) {
            captureController.capture()
            delay(100)
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Capturable(
            controller = captureController,
            onCaptured = { bitmap, _ ->
                bitmap?.let { background = it }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .systemBarsPadding()
                    .navigationBarsPadding()
                    .statusBarsPadding()
                    .imePadding()
            ) {
                PetDiaryTopBar()
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 20.dp)
                ) {
                    item {
                        DayCounter(modifier = Modifier.padding(horizontal = 24.dp))
                        Spacer(modifier = Modifier.height(if (selectedItemIndex == 1) 20.dp else 32.dp))
                    }

                    if (selectedItemIndex == 1) {
                        item {
                            DatePicker(
                                modifier = Modifier.padding(
                                    top = 15.dp, bottom = 35.dp, start = 30.dp, end = 30.dp
                                ),
                                selectedDate = selectedDate ?: LocalDate.now(),
                                onYearMonthChanged = { },
                                onDayClicked = {
                                    selectedDate = it
                                }
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(12.dp)
                                    .background(color = GalapagosTheme.colors.BgGray3)
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }

                    repeat(10) {
                        item {
                            DiaryItem(modifier = Modifier.padding(horizontal = 24.dp))
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            GlassmorphicTab(
                modifier = Modifier.padding(bottom = 16.dp),
                items = listOf("리스트", "캘린더"),
                backgroundBitmap = background,
                selectedItemIndex = selectedItemIndex,
                onClick = { selectedItemIndex = it }
            )
        }

        AddPetDiaryFB {
            appState.navigate(DiaryDestinations.ADD_DIARY)
        }
    }
}

@Preview
@Composable
private fun PetDiaryTopBar(
    onBack: () -> Unit = { },
    onBookmark: () -> Unit = { }
) {
    TopBar(
        modifier = Modifier.height(50.dp),
        leadingIconOnClick = { onBack() },
        content = "도랭이의 다이어리",
        trailingIcon = com.busymodernpeople.core.design.R.drawable.ic_bookmark_outlined,
        trailingIconOnClick = { onBookmark() }
    )
}

@Preview
@Composable
private fun DayCounter(
    modifier: Modifier = Modifier,
    allDay: Int = 60,
    recordDay: Int = 16
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = buildAnnotatedString {
                    append(text = "함께한 ")
                    withStyle(SpanStyle(color = GalapagosTheme.colors.PrimaryGreen)) {
                        append(text = "$allDay")
                    }
                    append(" 일 중 ")
                    withStyle(SpanStyle(color = GalapagosTheme.colors.PrimaryGreen)) {
                        append(text = "$recordDay")
                    }
                    append(" 일을 기록했어요!")
                },
                style = GalapagosTheme.typography.body1.copy(
                    color = GalapagosTheme.colors.FontGray1
                )
            )
        }
    }
}

@Preview
@Composable
private fun DiaryItem(
    modifier: Modifier = Modifier,
    date: String = "4월 13일",
    content: String = "ㅁ이라ㅓ님퍼ㅑㅐㅊ터퍁파ㅣㅌ피ㅓㄴ매퍄ㅓ;차퍼미;ㅏㅓㅣㅁ처태ㅑ퍼매ㅑㅌ처파ㅣㅌ첲미ㅏㅊ텊처탶ㅌ처ㅏㅣㅍ"
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date,
                style = GalapagosTheme.typography.title2.copy(
                    fontWeight = FontWeight.Bold,
                    color = GalapagosTheme.colors.FontBlack
                )
            )

            Icon(
                painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_bookmark_outlined),
                contentDescription = "IC_BOOKMARK",
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.img_dummy_animal),
            contentDescription = "IMG_DUMMY_ANIMAL",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = content,
            style = GalapagosTheme.typography.title5.copy(
                color = GalapagosTheme.colors.FontGray1
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.align(Alignment.End),
            text = "45분 전",
            style = GalapagosTheme.typography.body4.copy(
                color = GalapagosTheme.colors.FontGray2
            )
        )
    }
}

@Composable
private fun AddPetDiaryFB(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(bottom = 8.dp, end = 20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable { onClick() },
            painter = painterResource(id = R.drawable.ic_add_pet_diary),
            contentDescription = "IC_ADD_PET_DIARY",
            tint = Color.Unspecified
        )
    }
}