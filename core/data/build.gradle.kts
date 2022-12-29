@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("movies.android.library")
    id("movies.android.library.jacoco")
    id("movies.android.hilt")
    id("kotlinx-serialization")
    alias(libs.plugins.ksp)
}

android {
    defaultConfig {
        // The schemas directory contains a schema file for each version of the Room database.
        // This is required to enable Room auto migrations.
        // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        testInstrumentationRunner = "com.ipalermo.movies.core.testing.MoviesTestRunner"
    }
    namespace = "com.ipalermo.movies.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))

    testImplementation(project(":core:testing"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.common)
    implementation(libs.room.ktx)

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:data"))
    testImplementation(project(":core:data"))
}