package com.busymodernpeople.galapagos

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.busymodernpeople.core.design.R

// TODO : 후에 route를 각 feature의 startDestination로 교체
enum class TopLevelDestination(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    Home(
        label = R.string.tab_home,
        icon = R.drawable.ic_home,
        route = "homeGraph"
    ),
    Diary(
        label = R.string.tab_diary,
        icon = R.drawable.ic_diary,
        route = "diaryGraph"
    ),
    Community(
        label = R.string.tab_community,
        icon = R.drawable.ic_community,
        route = "communityGraph"
    ),
    MyPage(
        label = R.string.tab_my_page,
        icon = R.drawable.ic_my_page,
        route = "mypageGraph"
    )
}