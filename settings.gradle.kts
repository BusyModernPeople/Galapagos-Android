pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Galapagos"
include(":app")
include(":core:design")
include(":feature:login")
include(":feature:join")
include(":feature:home")
include(":feature:diary")
include(":feature:community")
include(":feature:mypage")