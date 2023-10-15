package com.busymodernpeople.core.common.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class TopLevelDestination(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    Home(
        label = com.busymodernpeople.core.design.R.string.tab_home,
        icon = com.busymodernpeople.core.design.R.drawable.ic_home,
        route = HomeDestinations.HOME
    ),
    Diary(
        label = com.busymodernpeople.core.design.R.string.tab_diary,
        icon = com.busymodernpeople.core.design.R.drawable.ic_diary,
        route = DiaryDestinations.DIARY
    ),
    Community(
        label = com.busymodernpeople.core.design.R.string.tab_community,
        icon = com.busymodernpeople.core.design.R.drawable.ic_community,
        route = CommunityDestinations.COMMUNITY
    ),
    MyPage(
        label = com.busymodernpeople.core.design.R.string.tab_my_page,
        icon = com.busymodernpeople.core.design.R.drawable.ic_my_page,
        route = "mypageGraph"
    )
}