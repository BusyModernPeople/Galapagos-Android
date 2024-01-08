package com.busymodernpeople.feature.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.busymodernpeople.core.common.base.DiaryDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GScrollableTabRow
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Preview
@Composable
fun DiaryScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .systemBarsPadding()
                .navigationBarsPadding()
                .imePadding()
        ) {
            DiaryTopBar()
            //NoDiaryContent()
            DiaryContent(appState)
        }
        AddDiaryFB {
            appState.navigate(DiaryDestinations.ADD_PET)
        }
    }
}

@Preview
@Composable
private fun DiaryTopBar() {
    Column {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 7.dp),
            text = "다이어리",
            style = GalapagosTheme.typography.title1.copy(
                color = GalapagosTheme.colors.FontBlack,
                fontWeight = FontWeight.Bold
            )
        )

        var selectedItemIndex by remember { mutableStateOf(0) }
        GScrollableTabRow(
            items = listOf("전체", "도마뱀", "거북이"),
            selectedItemIndex = selectedItemIndex,
            onClick = { selectedItemIndex = it }
        )
    }
}

@Composable
private fun NoDiaryContent(
    addDiaryOnClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(100.dp))
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.18f)
            .padding(horizontal = 24.dp),
        color = GalapagosTheme.colors.BgGray4,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "아직 등록된 동물이 없어요.",
                    style = GalapagosTheme.typography.title4.copy(
                        fontWeight = FontWeight.Medium,
                        color = GalapagosTheme.colors.FontGray1
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "버튼을 눌러 반려동물을 등록해주세요.",
                    style = GalapagosTheme.typography.body4.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray2
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                GButton(
                    modifier = Modifier.width(IntrinsicSize.Max),
                    buttonSize = ButtonSize.Height52,
                    shape = RoundedCornerShape(100.dp),
                    leadingIcon = R.drawable.ic_plus,
                    content = "동물 등록하기",
                    onClick = {
                        addDiaryOnClick()
                    }
                )
            }
        }
    }
}

@Composable
fun DiaryContent(appState: GalapagosAppState) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(6) {
            item {
                DiaryItem(
                    onClick = {
                        appState.navigate(DiaryDestinations.PET_DIARY)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun DiaryItem(
    isBookMarked: Boolean = true,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier.clickable { onClick() },
        contentAlignment = Alignment.TopEnd
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f),
            shape = RoundedCornerShape(8.dp),
            color = GalapagosTheme.colors.FontWhite,
            elevation = 10.dp
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.18f),
                    painter = painterResource(id = R.drawable.img_dummy_animal),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "도랭이",
                            style = GalapagosTheme.typography.title4.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = GalapagosTheme.colors.FontBlack
                            )
                        )
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.ic_male_20),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "함께한지",
                            style = GalapagosTheme.typography.body4.copy(
                                fontWeight = FontWeight.Normal,
                                color = GalapagosTheme.colors.FontGray1
                            )
                        )
                        Text(
                            text = "30일",
                            style = GalapagosTheme.typography.body4.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = GalapagosTheme.colors.FontBlack
                            )
                        )
                    }
                }
            }
        }
        if (isBookMarked) {
            Box(
                modifier = Modifier.padding(
                    top = 10.dp, end = 10.dp
                ),
                contentAlignment = Alignment.BottomStart
            ) {
                Marker()
            }
        }
    }
}

@Preview
@Composable
fun Marker() {
    Box(
        modifier = Modifier
            .background(
                shape = CircleShape,
                color = Color.White
            )
            .padding(7.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_star_green_16),
            tint = GalapagosTheme.colors.PrimaryGreen,
            contentDescription = null
        )
    }
}

@Composable
fun AddDiaryFB(
    onClick: () -> Unit
) {
    Column {
        Surface(
            shape = RoundedCornerShape(100.dp),
            elevation = 4.dp
        ) {
            GButton(
                modifier = Modifier.width(IntrinsicSize.Max),
                buttonSize = ButtonSize.Height52,
                shape = RoundedCornerShape(100.dp),
                leadingIcon = R.drawable.ic_plus,
                content = "동물 등록하기",
                onClick = {
                    onClick()
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}