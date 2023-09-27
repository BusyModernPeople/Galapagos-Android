package com.busymodernpeople.galapagos

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GalapagosApp : Application() {

    override fun onCreate() {
        super.onCreate()
        NaverIdLoginSDK.initialize(
            this,
            BuildConfig.NAVER_CLIENT_ID,
            BuildConfig.NAVER_CLIENT_SECRET,
            getString(R.string.app_name)
        )
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

}