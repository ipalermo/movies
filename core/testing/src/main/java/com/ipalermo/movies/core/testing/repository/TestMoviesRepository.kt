package com.ipalermo.movies.core.testing.repository

import androidx.paging.PagingData
import com.ipalermo.movies.core.data.repository.MoviesRepository
import com.ipalermo.movies.core.model.data.MovieResource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class TestMoviesRepository : MoviesRepository {

    private val movieResourcesFlow: MutableSharedFlow<List<MovieResource>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getMovieResourcesStream(): Flow<PagingData<MovieResource>> = movieResourcesFlow
        .map { PagingData.from(it) }

    override fun getMovieStream(id: Long): Flow<MovieResource> = movieResourcesFlow
        .mapNotNull { movies ->
            movies.find { it.id == id }
        }

    /**
     * A test-only API to allow controlling the list of movie resources from tests.
     */
    fun sendMovieResources(movieResources: List<MovieResource>) {
        movieResourcesFlow.tryEmit(movieResources)
    }
}
