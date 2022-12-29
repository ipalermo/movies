
pluginManagement {
    includeBuild("build-logic")
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
rootProject.name = "movies"
include(":app")
include(":benchmarks")
include(":core:common")
include(":core:data")
include(":core:data-test")
include(":core:database")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":core:testing")
include(":feature:discover")
include(":feature:movie")