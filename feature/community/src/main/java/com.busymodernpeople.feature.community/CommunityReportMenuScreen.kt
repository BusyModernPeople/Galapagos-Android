package com.busymodernpeople.feature.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.sp
import com.busymodernpeople.core.common.base.CommunityDestinations
import com.busymodernpeople.core.common.base.GalapagosAppState
import com.busymodernpeople.core.common.base.rememberGalapagosAppState
import com.busymodernpeople.core.design.ui.component.TopBar
import com.busymodernpeople.core.design.ui.theme.GalapagosTheme
import com.busymodernpeople.core.design.ui.theme.Pretendard
import com.busymodernpeople.feature.community.component.CommunityReportMenuItem

@Preview
@Composable
fun CommunityReportMenuScreen(
    appState: GalapagosAppState = rememberGalapagosAppState()
) {
    Column(
        modifier = Modifier
    ) {
        TopBar(
            content = "게시판 신고",
            leadingIconOnClick = { appState.navigateUp() }
        )
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
            modifier = Modifier.padding(horizontal = 36.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=광고")
                },
                content = "광고"
            )
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=폭언/욕설/혐오 발언")
                },
                content = "폭언/욕설/혐오 발언"
            )
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=불법성 정보")
                },
                content = "불법성 정보"
            )
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=음란성 정보")
                },
                content = "음란성 정보"
            )
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=개인정보 노출")
                },
                content = "개인정보 노출"
            )
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=주제와 무관한 글")
                },
                content = "주제와 무관한 글"
            )
            CommunityReportMenuItem(
                modifier = Modifier.clickable {
                    appState.navigate("${CommunityDestinations.REPORT_FORM}?title=기타")
                },
                content = "기타"
            )
        }
    }
}