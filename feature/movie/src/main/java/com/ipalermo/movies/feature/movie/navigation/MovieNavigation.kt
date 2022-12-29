package com.ipalermo.movies.feature.movie.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ipalermo.movies.feature.movie.MovieRoute

@VisibleForTesting
internal const val movieIdArg = "movieId"

internal class MovieArgs(savedStateHandle: SavedStateHandle) {
    val movieId: Long = checkNotNull(savedStateHandle[movieIdArg])
}

fun NavController.navigateToMovie(movieId: Long) {
    this.navigate("movie_route/$movieId")
}

fun NavGraphBuilder.movieScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "movie_route/{$movieIdArg}",
        arguments = listOf(
            navArgument(movieIdArg) { type = NavType.LongType }
        )
    ) {
        MovieRoute(onBackClick = onBackClick)
    }
}
