package com.busymodernpeople.feature.community

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.busymodernpeople.core.common.base.SheetContent
import com.busymodernpeople.core.design.R
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.feature.community.component.CommunityFreeItem

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommunityFreeScreen(
    navController: NavController = rememberNavController(),
    showBottomSheet: (SheetContent) -> Unit = { },
    hideBottomSheet: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        TopBar(content = "자유게시판", trailingIcon = R.drawable.ic_animal_category, leadingIconOnClick = { /*TODO*/ })
        CommunityInformBar()
        Spacer(modifier = Modifier.height(8.dp))
        CommunityAnimal()
        Spacer(modifier = Modifier.height(16.dp))
        CommunityContent()
        // GFloatingButton(modifier = Modifier.width(56.dp).height(56.dp), onClick = { /*TODO*/ }, icon = R.drawable.ic_write)
    }
}

@Preview
@Composable
private fun CommunityInformBar() {
    Box(
        modifier = Modifier.fillMaxWidth(1f),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier, shape = RoundedCornerShape(5.dp),
                    color = GalapagosTheme.colors.FontRed.copy(alpha = 0.2f)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                        text = "공지",
                        style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Bold),
                        color = GalapagosTheme.colors.FontRed
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier,
                    text = "질문금지, 상품판매 금지",
                    style = GalapagosTheme.typography.body2.copy(fontWeight = FontWeight.Normal),
                    color = GalapagosTheme.colors.FontRed
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "전체보기>",
                    style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.Normal),
                    color = GalapagosTheme.colors.FontRed
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommunityAnimal() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(18.dp))
        CommunityAnimalFilter(modifier = Modifier, content = "게코 도마뱀", onClick = { /* TODO */ })
    }
}

@Composable
private fun CommunityAnimalFilter(
    modifier: Modifier = Modifier,
    content: String = "",
    onClick: (index: Int) -> Unit
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = GalapagosTheme.colors.PrimaryGreen
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = content,
            style = GalapagosTheme.typography.body4.copy(fontWeight = FontWeight.SemiBold),
            color = GalapagosTheme.colors.FontWhite
        )
    }
}

@Preview
@Composable
private fun CommunityContent() {
    Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(5) {
            CommunityFreeItem()
        }
        ShowMoreButton()
    }
}

@OptIn(ExperimentalMaterialApi::class)
//@Preview
@Composable
private fun ShowMoreButton(
    onClick: () -> Unit = { }
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Text(
                text = "더보기",
                style = GalapagosTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = GalapagosTheme.colors.FontGray2
                )
            )

            Icon(
                painterResource(id = com.busymodernpeople.core.design.R.drawable.ic_show_more),
                contentDescription = null
            )
        }
    }
}