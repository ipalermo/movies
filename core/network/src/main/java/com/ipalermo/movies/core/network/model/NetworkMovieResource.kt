package com.ipalermo.movies.core.network.model

import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.network.model.util.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Network representation of [MovieResource] when fetched from /discover/movie
 */
@Serializable
data class NetworkMovieResource(
    val id: Long,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @Serializable(LocalDateSerializer::class)
    @SerialName("release_date")
    val releaseDate: LocalDate
)
