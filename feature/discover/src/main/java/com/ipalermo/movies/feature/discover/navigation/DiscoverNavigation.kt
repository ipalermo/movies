package com.ipalermo.movies.feature.discover.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ipalermo.movies.feature.discover.DiscoverRoute

const val discoverGraphRoutePattern = "discover_graph"
const val discoverRoute = "discover_route"

fun NavController.navigateToDiscoverGraph(navOptions: NavOptions? = null) {
    this.navigate(discoverGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.discoverGraph(
    navigateToMovie: (Long) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = discoverGraphRoutePattern,
        startDestination = discoverRoute
    ) {
        composable(route = discoverRoute) {
            DiscoverRoute(
                navigateToMovie = navigateToMovie,
            )
        }
        nestedGraphs()
    }
}
