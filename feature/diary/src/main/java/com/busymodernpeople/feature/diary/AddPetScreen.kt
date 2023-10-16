package com.busymodernpeople.feature.diary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.ButtonSize
import com.busymodernpeople.core.design.ui.component.DateField
import com.busymodernpeople.core.design.ui.component.DatePicker
import com.busymodernpeople.core.design.ui.component.GButton
import com.busymodernpeople.core.design.ui.component.GTextField
import com.busymodernpeople.core.design.ui.component.TextFieldSize
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import java.time.LocalDate

@Preview
@Composable
fun AddPetScreen(
    appState: GalapagosAppState = rememberGalapagosAppState(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        AddPetTopBar()

        PetDetails(
            showBottomSheet = showBottomSheet,
            hideBottomSheet = hideBottomSheet
        )
    }
}

@Composable
private fun PetDetails(
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableIntStateOf(0) }

    var selectedSpecies by remember { mutableStateOf("") }
    var selectedAdoptDate: LocalDate? by remember { mutableStateOf(null) }
    var selectedBirthDate: LocalDate? by remember { mutableStateOf(null) }
    var isBirthdateUnknown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        PetProfileImage(modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(56.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "이름*",
                    style = GalapagosTheme.typography.body1.copy(
                        fontWeight = FontWeight.Normal,
                        color = GalapagosTheme.colors.FontGray2
                    )
                )

                var isMain by remember { mutableStateOf(false) }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.clickable { isMain = !isMain },
                        painter = painterResource(
                            id = if (isMain) {
                                com.busymodernpeople.core.design.R.drawable.ic_circle_check_enabled
                            } else {
                                com.busymodernpeople.core.design.R.drawable.ic_circle_check_disabled
                            }
                        ),
                        contentDescription = "CIRCLE_CHECK",
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "대표동물 설정",
                        style = GalapagosTheme.typography.body2.copy(
                            color = GalapagosTheme.colors.FontGray1
                        )
                    )
                }
            }
            GTextField(
                textFieldSize = TextFieldSize.Height56,
                value = name,
                onValueChange = { name = it },
                showLength = true
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 성별 선택
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "성별*",
                style = GalapagosTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    color = GalapagosTheme.colors.FontGray2
                )
            )
            SelectionRadioButton(
                items = listOf("미구분", "수컷", "암컷"),
                selectedItem = selectedGender,
                onItemSelected = { selectedGender = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 종 선택
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "종 선택*",
                style = GalapagosTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    color = GalapagosTheme.colors.FontGray2
                )
            )
            GButton(
                modifier = Modifier.fillMaxWidth(),
                buttonSize = ButtonSize.Height56,
                content = "선택하기",
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = GalapagosTheme.colors.PrimaryGreen
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = GalapagosTheme.colors.PrimaryGreen
                ),
                onClick = { /*TODO*/ }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 입양일 선택
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "입양일*",
                style = GalapagosTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    color = GalapagosTheme.colors.FontGray2
                )
            )
            DateField(
                placeholderText = "입양일을 선택해주세요",
                showDataPicker = {
                    showBottomSheet {
                        Column(modifier = Modifier.padding(horizontal = 22.dp)) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "입양일",
                                    style = GalapagosTheme.typography.title3.copy(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GalapagosTheme.colors.FontBlack
                                    )
                                )
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .clickable { hideBottomSheet() },
                                    painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_close),
                                    contentDescription = "IC_CLOSE",
                                    tint = Color.Unspecified
                                )
                            }
                            DatePicker(
                                modifier = Modifier.padding(vertical = 30.dp),
                                selectedDate = selectedBirthDate ?: LocalDate.now(),
                                onYearMonthChanged = { },
                                onDayClicked = {
                                    selectedBirthDate = it
                                    hideBottomSheet()
                                }
                            )
                        }
                    }
                }
            )
            SelectionRadioButton(
                items = listOf("일", "월", "년"),
                selectedItem = 0, // 기본값은 "일"로 설정
                onItemSelected = { /*TODO*/ }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 탄생일 선택
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            BirthdateSection(isBirthdateUnknown) { isBirthdateUnknown = it }
            DateField(
                placeholderText = "탄생일을 선택해주세요",
                showDataPicker = {
                    showBottomSheet {
                        Column(modifier = Modifier.padding(horizontal = 22.dp)) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "탄생일",
                                    style = GalapagosTheme.typography.title3.copy(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GalapagosTheme.colors.FontBlack
                                    )
                                )
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .clickable { hideBottomSheet() },
                                    painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_close),
                                    contentDescription = "IC_CLOSE",
                                    tint = Color.Unspecified
                                )
                            }
                            DatePicker(
                                modifier = Modifier.padding(vertical = 30.dp),
                                selectedDate = selectedBirthDate ?: LocalDate.now(),
                                onYearMonthChanged = { },
                                onDayClicked = {
                                    selectedBirthDate = it
                                    hideBottomSheet()
                                }
                            )
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        GButton(
            modifier = Modifier.padding(bottom = 16.dp),
            buttonSize = ButtonSize.Height56,
            enabled = name.isNotEmpty() && selectedSpecies.isNotEmpty() && selectedAdoptDate != null,
            content = "다음",
            onClick = {  }
        )
    }
}

@Composable
private fun PetProfileImage(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp, bottom = 6.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.img_dummy_animal),
            contentScale = ContentScale.Crop,
            contentDescription = "DUMMY_PET"
        )

        Surface(
            shape = CircleShape,
            color = Color.White,
            elevation = 10.dp
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .padding(7.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_photo_18),
                contentDescription = "IC_PHOTO",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun BirthdateSection(
    isBirthdateUnknown: Boolean,
    onUnknownBirthdateChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "탄생일*",
            style = GalapagosTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = GalapagosTheme.colors.FontGray2
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable {
                    onUnknownBirthdateChange(!isBirthdateUnknown)
                },
                painter = painterResource(
                    id = if (isBirthdateUnknown) {
                        com.busymodernpeople.core.design.R.drawable.ic_circle_check_enabled
                    } else {
                        com.busymodernpeople.core.design.R.drawable.ic_circle_check_disabled
                    }
                ),
                contentDescription = "CIRCLE_CHECK",
                tint = Color.Unspecified
            )
            Text(
                text = "탄생일을 모르겠어요.",
                style = GalapagosTheme.typography.body2.copy(
                    color = GalapagosTheme.colors.FontGray1
                )
            )
        }
    }
}

@Composable
private fun SelectionRadioButton(
    items: List<String> = listOf("미구분", "수컷", "암컷"),
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items.forEachIndexed { index, item ->
            GButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                buttonSize = ButtonSize.Height56,
                content = item,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (index == selectedItem) {
                        GalapagosTheme.colors.PrimaryGreen
                    } else {
                        GalapagosTheme.colors.FontWhite
                    },
                    contentColor = if (index == selectedItem) {
                        Color.White
                    } else {
                        GalapagosTheme.colors.FontGray3
                    }
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (index == selectedItem) {
                        Color.Transparent
                    } else {
                        GalapagosTheme.colors.BgGray1
                    }
                ),
                onClick = {
                    onItemSelected(index)
                }
            )
        }
    }
}

@Composable
fun DateSelectionSection(
    title: String,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    hideBottomSheet: () -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 22.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = GalapagosTheme.typography.title3.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = GalapagosTheme.colors.FontBlack
                )
            )
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .clickable { hideBottomSheet() },
                painter = painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_close),
                contentDescription = "IC_CLOSE",
                tint = Color.Unspecified
            )
        }
        DatePicker(
            modifier = Modifier.padding(vertical = 30.dp),
            selectedDate = selectedDate ?: LocalDate.now(),
            onYearMonthChanged = { },
            onDayClicked = {
                onDateSelected(it)
                hideBottomSheet()
            }
        )
    }
}

@Composable
private fun AddPetTopBar() {
    TopBar(
        leadingIconOnClick = { /*TODO*/ },
        content = "반려동물 등록"
    )
}
