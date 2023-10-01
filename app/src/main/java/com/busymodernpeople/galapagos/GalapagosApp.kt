package com.busymodernpeople.galapagos

import android.app.Application
import android.util.Log
import com.busymodernpeople.data.repository.DataStoreRepository
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class GalapagosApp : Application() {

    @Inject
    lateinit var dataStoreRepository: DataStoreRepository

    override fun onCreate() {
        super.onCreate()
        initializeThirdPartyLibraries()
        fetchAndSetFcmToken()
    }

    private fun initializeThirdPartyLibraries() {
        NaverIdLoginSDK.initialize(
            this,
            BuildConfig.NAVER_CLIENT_ID,
            BuildConfig.NAVER_CLIENT_SECRET,
            getString(R.string.app_name)
        )
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

    private fun fetchAndSetFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                setFcmTokenWithCoroutine(token)
            } else {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
            }
        }
    }

    private fun setFcmTokenWithCoroutine(token: String?) {
        token?.let {
            CoroutineScope(Dispatchers.IO).launch {
                dataStoreRepository.setFcmToken(it)
            }
        }
    }
}
