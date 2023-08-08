plugins {
    with(Plugins) {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
    }
}

android {
    namespace = "com.busymodernpeople.core.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        buildConfigField("String", "BASE_URL", "\"${properties["base.url"]}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
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
        implementation(APPCOMPAT)
        implementation(CORE_KTX)
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(UI_TEST_JUNIT4)
    }

    with(Dependencies.Google) {
        implementation(HILT_ANDROID)
    }

    with(Dependencies.SquareUp) {
        implementation(RETROFIT)
        implementation(CONVERTER_GSON)
        implementation(OKHTTP)
        implementation(LOGGING_INTERCEPTOR)
    }

    with(Dependencies.Junit) {
        testImplementation(JUNIT)
    }
}