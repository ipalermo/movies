
plugins {
    id("movies.android.feature")
    id("movies.android.library.compose")
    id("movies.android.library.jacoco")
}

android {
    namespace = "com.ipalermo.movies.feature.movie"
}

dependencies {
    implementation(libs.kotlinx.datetime)
}
