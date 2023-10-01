package com.busymodernpeople.galapagos

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.d("fcm_token", token.toString())
        })
    }

}