import java.util.Properties

plugins {
    with(Plugins) {
        id(ANDROID_LIBRARY)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
    }
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.busymodernpeople.feature.auth"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "GOOGLE_OAUTH_CLIENT_ID",
            properties["GOOGLE_OAUTH_CLIENT_ID"] as String
        )
        buildConfigField(
            "String",
            "GOOGLE_OAUTH_CLIENT_SECRET",
            properties["GOOGLE_OAUTH_CLIENT_SECRET"] as String
        )
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
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:design"))
    implementation(project(":data"))

    with(Dependencies.AndroidX) {
        implementation(CORE_KTX)
        implementation(COMPOSE_MATERIAL)
        implementation(COMPOSE_UI)
        implementation(COMPOSE_UI_TOOLING_PREVIEW)
        implementation(NAVIGATION_COMPOSE)
        implementation(HILT_NAVIGATION_COMPOSE)
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(UI_TEST_JUNIT4)
        debugImplementation(UI_TOOLING)
        debugImplementation(UI_TEST_MANIFEST)
    }

    with(Dependencies.Google) {
        kapt(HILT_ANDROID_COMPILER)
        implementation(HILT_ANDROID)
    }

    with(Dependencies.Junit) {
        testImplementation(JUNIT)
    }

    implementation("com.google.android.gms:play-services-auth:20.6.0")
}