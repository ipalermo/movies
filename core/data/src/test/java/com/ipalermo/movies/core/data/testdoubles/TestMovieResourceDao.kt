package com.ipalermo.movies.core.data.testdoubles

import androidx.paging.PagingSource
import com.ipalermo.movies.core.database.dao.MovieResourceDao
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import java.time.LocalDate

/**
 * Test double for [MovieResourceDao]
 */
class TestMovieResourceDao : MovieResourceDao {

    private var entitiesStateFlow = MutableStateFlow(
        listOf(
            MovieResourceEntity(
                movieId = 12345,
                title = "Title",
                overview = "overview",
                posterUrl = "https://image.example.org/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
                releaseDate = LocalDate.of(2022, 1, 1)
            )
        )
    )

    override fun getMovieResourcesPagingSource(): PagingSource<Int, MovieResourceEntity> =
        throw NotImplementedError("Unused in tests")

    override fun getMovieResourcesStream(): Flow<List<MovieResourceEntity>> = entitiesStateFlow

    override fun getMovieResourceEntityStream(movieId: Long) = entitiesStateFlow
        .mapNotNull { movies ->
            movies.find { it.movieId == movieId }
        }

    override suspend fun insertAll(
        entities: List<MovieResourceEntity>
    ): List<Long> {
        entitiesStateFlow.value = entities
        // Assume no conflicts on insert
        return entities.map { it.id.toLong() }
    }

    override suspend fun deleteMovieResources() {
        entitiesStateFlow.update { emptyList() }
    }
}
