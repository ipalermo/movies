package com.ipalermo.movies.core.data.model

import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.network.BuildConfig
import com.ipalermo.movies.core.network.model.NetworkMovieResource

fun NetworkMovieResource.asEntity() = MovieResourceEntity(
    movieId = id,
    title = title,
    overview = overview,
    posterUrl = BuildConfig.TMDB_IMAGE_BASE_URL + POSTER_PATH_WIDTH + posterPath,
    releaseDate = releaseDate
)

const val POSTER_PATH_WIDTH = "w342"
// TODO dynamically calculate the optimal width depending on the movie cards width on the device and orientation