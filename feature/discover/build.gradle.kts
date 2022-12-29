
plugins {
    id("movies.android.feature")
    id("movies.android.library.compose")
    id("movies.android.library.jacoco")
}

android {
    namespace = "com.ipalermo.movies.feature.discover"
}

dependencies {
    implementation(libs.kotlinx.datetime)

    implementation(libs.accompanist.flowlayout)

    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
}
