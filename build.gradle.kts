plugins {
    with(Plugins) {
        id(ANDROID_APPLICATION) version Versions.Plugins.AGP apply false
        id(ANDROID_LIBRARY) version Versions.Plugins.AGP apply false
        id(KOTLIN_ANDROID) version Versions.Plugins.KOTLIN_ANDROID apply false
        id(DAGGER_HILT_ANDROID) version Versions.Plugins.DAGGER_HILT_ANDROID apply false
    }
}