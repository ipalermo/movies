package com.ipalermo.movies.navigation

import com.ipalermo.movies.R

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val titleTextId: Int
) {
    DISCOVER(
        titleTextId = R.string.app_name
    )
}
