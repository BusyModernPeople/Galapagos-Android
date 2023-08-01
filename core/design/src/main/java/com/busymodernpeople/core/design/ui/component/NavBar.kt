package com.busymodernpeople.core.design.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme

sealed class Tabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Home : Tabs(R.string.tab_home, R.drawable.ic_home, "/home")
    object Diary : Tabs(R.string.tab_diary, R.drawable.ic_diary, "/diary")
    object Community : Tabs(R.string.tab_community, R.drawable.ic_community, "/community")
    object MyPage : Tabs(R.string.tab_my_page, R.drawable.ic_my_page, "/mypage")
}

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    selectedContentColor: Color = GalapagosTheme.colors.PrimaryGreen,
    unselectedContentColor: Color = GalapagosTheme.colors.FontGray4,
    navController: NavController
) {
    val tabs = arrayOf(Tabs.Home, Tabs.Diary, Tabs.Community, Tabs.MyPage)

    Surface(
        modifier = modifier.shadow(
            elevation = 10.dp,
            spotColor = Color(0x1A000000),
            ambientColor = Color(0x1A000000)
        ),
        color = backgroundColor,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                tabs.forEachIndexed { index, tab ->
                    val selected = currentRoute == tab.route

                    NavItem(
                        icon = tab.icon,
                        label = tab.title,
                        selected = selected,
                        selectedContentColor = selectedContentColor,
                        unselectedContentColor = unselectedContentColor,
                        onClick = {
                            navController.navigate(tab.route) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it)
                                }
                                launchSingleTop = true
                            }
                        }
                    )

                    if (index < tabs.size - 1) {
                        Spacer(modifier = Modifier.width(33.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    selected: Boolean,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit
) {
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(percent = 50))
            .background(color = Color.Transparent)
            .padding(start = 6.dp, end = 6.dp)
            .clickable(onClick = onClick),
        onClick = onClick,
        interactionSource = NoRippleInteractionSource()
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(id = label),
                    tint = contentColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = label),
                    style = GalapagosTheme.typography.body4,
                    fontWeight = FontWeight.SemiBold,
                    color = contentColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavBar() {
    GalapagosTheme {
        NavBar(navController = rememberNavController())
    }
}