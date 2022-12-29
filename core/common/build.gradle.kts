plugins {
    id("movies.android.library")
    id("movies.android.library.jacoco")
    id("movies.android.hilt")
}

android {
    namespace = "com.ipalermo.movies.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}