object Dependencies {

    object AndroidX {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_RUNTIME_COMPOSE = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.LIFECYCLE}"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.AndroidX.ACTIVITY_COMPOSE}"
        const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.AndroidX.COMPOSE_UI}"
        const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.COMPOSE_UI}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.AndroidX.COMPOSE_UI}"
        const val CONSTRAINT_LAYOUT_COMPOSE = "androidx.constraintlayout:constraintlayout-compose:${Versions.AndroidX.CONSTRAINT_LAYOUT_COMPOSE}"
        const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${Versions.AndroidX.NAVIGATION_COMPOSE}"
        const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Versions.AndroidX.HILT_NAVIGATION_COMPOSE}"
        const val PAGING_RUNTIME = "androidx.paging:paging-runtime:${Versions.AndroidX.PAGING_RUNTIME}"
        const val PAGING_COMPOSE = "androidx.paging:paging-compose:${Versions.AndroidX.PAGING_COMPOSE}"

        const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.AndroidX.EXT_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.AndroidX.ESPRESSO_CORE}"
        const val UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:${Versions.AndroidX.COMPOSE_UI}"

        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.AndroidX.COMPOSE_UI}"
        const val UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.AndroidX.COMPOSE_UI}"
    }

    object Jetbrains {
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Jetbrains.COROUTINES}"
    }

    object Google {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.Google.DAGGER}"
        const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.Google.DAGGER}"
    }

    object SquareUp {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.SquareUp.RETORIT2}"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.SquareUp.RETORIT2}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.SquareUp.OKHTTP3}"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.SquareUp.OKHTTP3}"
    }

    object Tools {
        const val DESUGAR_JDK_LIBS = "com.android.tools:desugar_jdk_libs:${Versions.Tools.DESUGAR_JDK_LIBS}"
    }

    object Junit {
        const val JUNIT = "junit:junit:${Versions.JUnit.JUNIT}"
    }
}