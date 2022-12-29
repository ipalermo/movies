package com.ipalermo.movies.core.data.repository

import androidx.paging.PagingData
import com.ipalermo.movies.core.model.data.MovieResource
import kotlinx.coroutines.flow.Flow

/**
 * Data layer implementation for [MovieResource]
 */
interface MoviesRepository {
    /**
     * Returns available movie resources as a stream.
     */
    fun getMovieResourcesStream(): Flow<PagingData<MovieResource>>

    /**
     * Gets data for a specific movie
     */
    fun getMovieStream(id: Long): Flow<MovieResource>
}
