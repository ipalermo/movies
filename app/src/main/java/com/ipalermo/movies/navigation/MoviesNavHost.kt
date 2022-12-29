package com.ipalermo.movies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ipalermo.movies.feature.discover.navigation.*
import com.ipalermo.movies.feature.discover.navigation.discoverGraph
import com.ipalermo.movies.feature.movie.navigation.movieScreen
import com.ipalermo.movies.feature.movie.navigation.navigateToMovie

/**
 * Top-level navigation graph
 */
@Composable
fun MoviesNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = discoverGraphRoutePattern
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        discoverGraph(
            navigateToMovie = { movieId ->
                navController.navigateToMovie(movieId)
            },
            nestedGraphs = {
                movieScreen(onBackClick)
            }
        )
    }
}
