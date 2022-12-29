package com.ipalermo.movies.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ipalermo.movies.core.database.MoviesDatabase
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.database.model.asExternalModel
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertTrue

class MovieResourceDaoTest {

    private lateinit var movieResourceDao: MovieResourceDao
    private lateinit var db: MoviesDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MoviesDatabase::class.java
        ).build()
        movieResourceDao = db.movieResourceDao()
    }

    @Test
    fun movieResourceDao_fetches_items_by_ascending_id() = runTest {
        val movieResourceEntities = listOf(
            testMovieResourceEntity(1),
            testMovieResourceEntity(2),
            testMovieResourceEntity(3),
            testMovieResourceEntity(4),
        )
        movieResourceDao.insertAll(movieResourceEntities)

        val savedMovieResourceEntities = movieResourceDao.getMovieResourcesStream()
            .first()

        assertEquals(
            movieResourceEntities.map { it.movieId },
            savedMovieResourceEntities.map { it.asExternalModel().id }
        )
    }

    @Test
    fun movieResourceDao_retrieves_item_by_movie_id() =
        runTest {
            val movieResourceEntities = listOf(
                testMovieResourceEntity(1),
                testMovieResourceEntity(2),
                testMovieResourceEntity(3),
                testMovieResourceEntity(4),
            )

            movieResourceDao.insertAll(movieResourceEntities)
            val retrievedMovie = movieResourceDao.getMovieResourceEntityStream(movieId = 3).first()

            assertEquals(
                movieResourceEntities[2].movieId,
                retrievedMovie.movieId
            )
        }

    @Test
    fun movieResourceDao_deletes_all_movies() = runTest {
        val movieResourceEntities = listOf(
            testMovieResourceEntity(1),
            testMovieResourceEntity(2),
            testMovieResourceEntity(3),
            testMovieResourceEntity(4),
        )

        movieResourceDao.insertAll(movieResourceEntities)
        movieResourceDao.deleteMovieResources()

        val savedMovieResourceEntities = movieResourceDao.getMovieResourcesStream()
            .first()

        assertTrue { savedMovieResourceEntities.isEmpty() }
    }
}

private fun testMovieResourceEntity(movieId: Long) = MovieResourceEntity(
    movieId = movieId,
    title = "",
    overview = "",
    posterUrl = "",
    releaseDate = LocalDate.of(2022, 1, 1)
)
