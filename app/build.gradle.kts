plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_17}"
    }
    buildFeatures {
        compose = true
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
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_COMPOSE)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)
    implementation(Dependencies.AndroidX.COMPOSE_UI)
    implementation(Dependencies.AndroidX.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Dependencies.AndroidX.COMPOSE_MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT_COMPOSE)
    implementation(Dependencies.AndroidX.NAVIGATION_COMPOSE)
    implementation(Dependencies.AndroidX.HILT_NAVIGATION_COMPOSE)
    implementation(Dependencies.AndroidX.PAGING_RUNTIME)
    implementation(Dependencies.AndroidX.PAGING_COMPOSE)

    implementation(Dependencies.Jetbrains.COROUNTES)

    implementation(Dependencies.Google.HILT_ANDROID)
    implementation(Dependencies.Google.MATERIAL)
    kapt(Dependencies.Google.HILT_ANDROID_COMPILER)

    implementation(Dependencies.SquareUp.RETROFIT)
    implementation(Dependencies.SquareUp.CONVERTER_GSON)

    implementation(Dependencies.SquareUp.OKHTTP)
    implementation(Dependencies.SquareUp.LOGGING_INTERCEPTOR)

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
}