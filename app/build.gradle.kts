plugins {
    with(Plugins) {
        id(ANDORID_APPLICATION)
        id(KOTLIN_ANDROID)
        id(KOTLIN_KAPT)
        id(DAGGER_HILT_ANDROID)
    }
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

    with(Dependencies.Tools) {
        coreLibraryDesugaring(DESUGAR_JDK_LIBS)
    }

    with(Dependencies.Junit) {
        testImplementation(JUNIT)
    }
}