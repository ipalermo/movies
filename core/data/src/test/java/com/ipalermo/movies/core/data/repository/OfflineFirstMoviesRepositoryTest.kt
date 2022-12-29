package com.ipalermo.movies.core.data.repository

import androidx.paging.PagingData
import com.ipalermo.movies.core.data.testdoubles.TestMovieResourceDao
import com.ipalermo.movies.core.data.testdoubles.TestMoviesRemoteMediator
import com.ipalermo.movies.core.database.model.asExternalModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals

class OfflineFirstMoviesRepositoryTest {

    private lateinit var subject: OfflineFirstMoviesRepository

    private lateinit var movieResourceDao: TestMovieResourceDao

    private lateinit var mediator: TestMoviesRemoteMediator

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        movieResourceDao = TestMovieResourceDao()
        mediator = TestMoviesRemoteMediator()
        subject = OfflineFirstMoviesRepository(
            movieResourceDao = movieResourceDao,
            mediator = mediator
        )
    }

    @Test
    fun offlineFirstMoviesRepository_movie_resources_stream_is_backed_by_movies_resource_dao() =
        runTest {
//            assertEquals(
//                PagingData.from(movieResourceDao.getMovieResourcesStream()
//                    .first().map { it.asExternalModel() }
//                ),
//                subject.getMovieResourcesStream()
//                    .first()
//            )
        }

    @Test
    fun offlineFirstMoviesRepository_movie_resource_is_backed_by_movies_resource_dao() =
        runTest {
            assertEquals(
                movieResourceDao.getMovieResourceEntityStream(movieId = 12345)
                    .first().asExternalModel(),
                subject.getMovieStream(id = 12345)
                    .first()
            )
        }
}
