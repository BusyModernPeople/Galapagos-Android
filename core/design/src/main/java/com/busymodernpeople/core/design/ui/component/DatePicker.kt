package com.busymodernpeople.core.design.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.Pretendard
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MonthSelector(
    modifier: Modifier = Modifier,
    currentMonth: YearMonth,
    prevBtnOnClick: () -> Unit,
    nextBtnOnClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            IconButton(
                onClick = prevBtnOnClick
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_prev_month
                    ),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
            }

            Text(
                text = "${currentMonth.year}년 ${currentMonth.monthValue}월",
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = GalapagosTheme.colors.FontBlack,
                    lineHeight = 1.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )

            IconButton(
                onClick = nextBtnOnClick
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_next_month
                    ),
                    tint = Color.Unspecified,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun WeekHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val weekdays = listOf("일", "월", "화", "수", "목", "금", "토")

        for (day in weekdays) {
            Box(
                modifier = Modifier.size(42.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    style = GalapagosTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold,
                    color = GalapagosTheme.colors.FontGray2
                )
            }
        }
    }
}

@Composable
private fun Week(
    modifier: Modifier = Modifier,
    weekNumber: Int,
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    markedDates: List<LocalDate>,
    onDayClicked: (LocalDate) -> Unit
) {
    val beginningWeek = currentMonth.atDay(1).plusWeeks(weekNumber.toLong())
    var currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(7) {
            if (currentDay.month == currentMonth.month) {
                Day(
                    day = currentDay,
                    selectedDate = selectedDate,
                    isMarked = markedDates.contains(currentDay),
                    onDayClicked = onDayClicked
                )
            } else {
                Box(modifier = Modifier.size(42.dp))
            }
            currentDay = currentDay.plusDays(1)
        }
    }
}

@Composable
private fun Day(
    modifier: Modifier = Modifier,
    day: LocalDate,
    isMarked: Boolean,
    selectedDate: LocalDate,
    onDayClicked: (LocalDate) -> Unit
) {
    val selected = day == selectedDate
    val isToday = day == LocalDate.now()
    val todayIsSelected = LocalDate.now() == selectedDate

    Box(
        modifier = modifier.size(42.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    shape = CircleShape,
                    color = if (selected) GalapagosTheme.colors.PrimaryGreen else Color.Transparent
                )
                .clickable(
                    interactionSource = NoRippleInteractionSource(),
                    indication = null,
                    onClick = { onDayClicked(day) }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isMarked) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                color = GalapagosTheme.colors.PrimaryGreen,
                                shape = CircleShape
                            )
                    )
                }
            }

            Text(
                text = "${day.dayOfMonth}",
                style = GalapagosTheme.typography.body2,
                color = if (selected) {
                    Color.White
                } else if (!todayIsSelected && isToday) {
                    GalapagosTheme.colors.PrimaryGreen
                } else {
                    GalapagosTheme.colors.FontGray1
                },
                fontWeight = if (todayIsSelected && isToday) {
                    FontWeight.SemiBold
                } else {
                    FontWeight.Medium
                }
            )
        }
    }
}

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    markedDates: List<LocalDate> = emptyList(),
    currentYearMonth: YearMonth = YearMonth.from(LocalDate.now()),
    onYearMonthChanged: (YearMonth) -> Unit,
    onDayClicked: (LocalDate) -> Unit
) {
    var currentMonth by remember { mutableStateOf(currentYearMonth) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonthSelector(
            currentMonth = currentMonth,
            prevBtnOnClick = {
                currentMonth = currentMonth.minusMonths(1)
                onYearMonthChanged(currentMonth)
            },
            nextBtnOnClick = {
                currentMonth = currentMonth.plusMonths(1)
                onYearMonthChanged(currentMonth)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        WeekHeader()

        for (num in 0 until currentMonth.getNumberWeeks()) {
            Week(
                weekNumber = num,
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                markedDates = markedDates,
                onDayClicked = onDayClicked
            )
        }
    }
}

fun YearMonth.getNumberWeeks(weekFields: WeekFields = WeekFields.SUNDAY_START): Int {
    val firstWeekNumber = this.atDay(1)[weekFields.weekOfMonth()]
    val lastWeekNumber = this.atEndOfMonth()[weekFields.weekOfMonth()]

    return lastWeekNumber - firstWeekNumber + 1
}


@Preview(showBackground = true)
@Composable
fun PreviewDatePicker() {
    GalapagosTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            var selectedDate by remember { mutableStateOf(LocalDate.now()) }

            DatePicker(
                selectedDate = selectedDate,
                markedDates = listOf(LocalDate.now().minusDays(2)),
                onYearMonthChanged = { },
                onDayClicked = { selectedDate = it }
            )
        }
    }
}