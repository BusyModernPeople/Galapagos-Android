plugins {
    with(Plugins) {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
    }
}

android {
    namespace = "com.busymodernpeople.core.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    with(Dependencies.AndroidX) {
        implementation(CORE_KTX)
        implementation(COMPOSE_UI)
        implementation(LIFECYCLE_VIEWMODEL_COMPOSE)
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(UI_TEST_JUNIT4)
    }

    with(Dependencies.Google) {
        implementation(HILT_ANDROID)
    }

    with(Dependencies.Junit) {
        testImplementation(JUNIT)
    }
}