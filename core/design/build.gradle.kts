plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.busymodernpeople.core.design"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    with(Dependencies.AndroidX) {
        implementation(APPCOMPAT)
        implementation(CORE_KTX)
        implementation(COMPOSE_UI)
        implementation(COMPOSE_UI_TOOLING_PREVIEW)
        implementation(COMPOSE_MATERIAL)
        implementation(CONSTRAINT_LAYOUT_COMPOSE)
        implementation(NAVIGATION_COMPOSE)
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(UI_TEST_JUNIT4)
        debugImplementation(UI_TOOLING)
        debugImplementation(UI_TEST_MANIFEST)
    }

    with(Dependencies.Tools) {
        coreLibraryDesugaring(DESUGAR_JDK_LIBS)
    }

    with(Dependencies.Junit) {
        testImplementation(JUNIT)
    }
}