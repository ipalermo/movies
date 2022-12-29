plugins {
    id("movies.android.library")
    id("movies.android.library.compose")
    id("movies.android.hilt")
}

android {
    namespace = "com.ipalermo.movies.core.testing"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    api(libs.junit4)
    api(libs.androidx.test.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)

    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.runner)
    api(libs.androidx.test.rules)
    api(libs.androidx.compose.ui.test)
    api(libs.androidx.paging.common)
    api(libs.hilt.android.testing)

    debugApi(libs.androidx.compose.ui.testManifest)
}