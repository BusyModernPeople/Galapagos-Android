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
    namespace = "com.busymodernpeople.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "BASE_URL",
            properties["base.url"] as String
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:common"))

    with(Dependencies.AndroidX) {
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
    }

    with(Dependencies.SquareUp) {
        implementation(RETROFIT)
        implementation(CONVERTER_GSON)
        implementation(OKHTTP)
        implementation(LOGGING_INTERCEPTOR)
    }    
    
    with(Dependencies.Jetbrains) {
        implementation(COROUTINES)
    }
    
    with(Dependencies.Google) {
        kapt(HILT_ANDROID_COMPILER)
        implementation(HILT_ANDROID)
    }
    
    with(Dependencies.Junit) {
        testImplementation(JUNIT)
    }
}