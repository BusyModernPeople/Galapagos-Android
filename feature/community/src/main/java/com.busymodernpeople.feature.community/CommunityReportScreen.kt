package com.busymodernpeople.feature.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.Pretendard
import com.busymodernpeople.feature.community.component.CommunityReportMenuItem

@Preview
@Composable
fun CommunityReportScreen() {
    Column(
        modifier = Modifier
    ) {
        TopBar(content = "게시판 신고", leadingIconOnClick = { /*TODO*/ })
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "신고 이유가 무엇인가요?",
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = GalapagosTheme.colors.FontGray1,
                    lineHeight = 27.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "신고 내역은 갈라파고스팀만 볼 수 있어요.\n허위 신고 시 사용이 제한될 수 있어요.",
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = GalapagosTheme.colors.FontGray3,
                    lineHeight = 24.sp,
                )
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.padding(horizontal = 36.dp)
        ) {
            CommunityReportMenuItem(modifier = Modifier, "광고")
            Spacer(modifier = Modifier.height(26.dp))
            CommunityReportMenuItem(modifier = Modifier, "폭언/욕설/혐오 발언")
            Spacer(modifier = Modifier.height(26.dp))
            CommunityReportMenuItem(modifier = Modifier, "불법성 정보")
            Spacer(modifier = Modifier.height(26.dp))
            CommunityReportMenuItem(modifier = Modifier, "음란성 정보")
            Spacer(modifier = Modifier.height(26.dp))
            CommunityReportMenuItem(modifier = Modifier, "개인정보 노출")
            Spacer(modifier = Modifier.height(26.dp))
            CommunityReportMenuItem(modifier = Modifier, "주제와 무관한 글")
            Spacer(modifier = Modifier.height(26.dp))
            CommunityReportMenuItem(modifier = Modifier, "기타")
        }
    }
}