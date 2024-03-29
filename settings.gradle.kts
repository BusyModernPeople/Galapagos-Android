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
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}
rootProject.name = "Galapagos"
include(":app")
include(":core:design")
include(":core:common")
include(":feature:auth")
include(":feature:home")
include(":feature:diary")
include(":feature:community")
include(":feature:mypage")
include(":data")
