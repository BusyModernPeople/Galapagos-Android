package com.busymodernpeople.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

@Composable
fun SelectBreedDialog(
    majorClassList: List<String>,
    subclassList: List<String>,
    onSelect: (String) -> Unit,
) {
    var selectedMajorClassIndex: Int? by remember { mutableStateOf(null) }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .systemBarsPadding()
                .navigationBarsPadding()
                .imePadding()
        ) {
            SelectBreedTopBar()
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    itemsIndexed(majorClassList) { index, majorClass ->
                        val isSelected = selectedMajorClassIndex == index

                        MajorClassItem(
                            isSelected = isSelected,
                            majorClass = majorClass,
                            onClick = { selectedMajorClassIndex = index }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .width(43.dp)
                        .fillMaxHeight()
                )

                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "파충류",
                            style = GalapagosTheme.typography.body3.copy(
                                color = GalapagosTheme.colors.FontGray2
                            )
                        )
                    }

                    items(subclassList) { subclass ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(subclass) },
                            text = subclass,
                            style = GalapagosTheme.typography.title5.copy(
                                color = GalapagosTheme.colors.FontGray1,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MajorClassItem(
    isSelected: Boolean,
    majorClass: String,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        GalapagosTheme.colors.PrimaryGreen
    } else {
        Color.Transparent
    }
    val textColor = if (isSelected) {
        Color.White
    } else {
        GalapagosTheme.colors.FontBlack
    }

    Text(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
            )
            .width(132.dp)
            .padding(horizontal = 24.dp, vertical = 14.dp)
            .clickable(onClick = onClick),
        text = majorClass,
        style = GalapagosTheme.typography.title5.copy(color = textColor)
    )
}

@Preview
@Composable
fun SelectBreedTopBar(
    onClick: () -> Unit = {}
) {
    TopBar(
        content = "종 선택하기",
        leadingIcon = null,
        trailingIcon = com.busymodernpeople.core.design.R.drawable.ic_close,
        trailingIconOnClick = { onClick() }
    )
}
