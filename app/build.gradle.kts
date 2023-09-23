import java.util.Properties

plugins {
    with(Plugins) {
        id(ANDROID_APPLICATION)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
        id(DAGGER_HILT_ANDROID)
    }
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.busymodernpeople.galapagos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.busymodernpeople.galapagos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "KAKAO_NATIVE_APP_KEY",
            properties["kakao.native.app.key"] as String
        )
        resValue(
            "string",
            "KAKAO_NATIVE_APP_KEY_FULL",
            properties["kakao.native.app.key.full"] as String
        )
        buildConfigField(
            "String",
            "NAVER_CLIENT_ID",
            properties["naver.client.id"] as String
        )
        buildConfigField(
            "String",
            "NAVER_CLIENT_SECRET",
            properties["naver.client.secret"] as String
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_17}"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:design"))
    implementation(project(":core:common"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:diary"))
    implementation(project(":feature:community"))
    implementation(project(":feature:mypage"))

    with(Dependencies.AndroidX) {
        implementation(APPCOMPAT)
        implementation(CORE_KTX)
        implementation(LIFECYCLE_RUNTIME_KTX)
        implementation(LIFECYCLE_RUNTIME_COMPOSE)
        implementation(LIFECYCLE_VIEWMODEL_COMPOSE)
        implementation(ACTIVITY_COMPOSE)
        implementation(COMPOSE_UI)
        implementation(COMPOSE_UI_TOOLING_PREVIEW)
        implementation(COMPOSE_MATERIAL)
        implementation(CONSTRAINT_LAYOUT_COMPOSE)
        implementation(NAVIGATION_COMPOSE)
        implementation(HILT_NAVIGATION_COMPOSE)
        implementation(PAGING_RUNTIME)
        implementation(PAGING_COMPOSE)
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(UI_TEST_JUNIT4)
        debugImplementation(UI_TOOLING)
        debugImplementation(UI_TEST_MANIFEST)
    }

    with(Dependencies.Jetbrains) {
        implementation(COROUTINES)
    }

    with(Dependencies.Google) {
        implementation(HILT_ANDROID)
        implementation(MATERIAL)
        kapt(HILT_ANDROID_COMPILER)
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

    implementation("com.kakao.sdk:v2-user:2.15.0")
    implementation("com.navercorp.nid:oauth:5.7.0")
}