package com.ipalermo.movies.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ipalermo.movies.core.model.data.MovieResource
import java.time.LocalDate

/**
 * Defines a movie resource.
 */
@Entity(
    tableName = "movie_resources"
)
data class MovieResourceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    val title: String,
    val overview: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: LocalDate,
)

fun MovieResourceEntity.asExternalModel() = MovieResource(
    id = movieId,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    releaseDate = releaseDate
)
