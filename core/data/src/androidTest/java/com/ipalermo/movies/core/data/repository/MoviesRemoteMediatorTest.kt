package com.ipalermo.movies.core.data.repository

import android.content.Context
import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ipalermo.movies.core.database.MoviesDatabase
import com.ipalermo.movies.core.database.dao.MovieResourceDao
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.network.MoviesNetworkDataSource
import com.ipalermo.movies.core.database.dao.MovieKeysDao
import com.ipalermo.movies.core.network.fake.EmptyMoviesNetworkDataSource
import com.ipalermo.movies.core.network.fake.FailureMoviesNetworkDataSource
import com.ipalermo.movies.core.network.fake.SuccessMoviesNetworkDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
class MoviesRemoteMediatorTest {

    private lateinit var movieResourceDao: MovieResourceDao
    private lateinit var movieKeysDao: MovieKeysDao
    private lateinit var db: MoviesDatabase
    private lateinit var api: MoviesNetworkDataSource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MoviesDatabase::class.java
        ).build()
        movieResourceDao = db.movieResourceDao()
        movieKeysDao = db.movieKeysDao()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        val remoteMediator = MoviesRemoteMediator(
            moviesDatabase = db,
            movieResourceDao = movieResourceDao,
            movieKeysDao = movieKeysDao,
            network = SuccessMoviesNetworkDataSource()
        )
        val pagingState = PagingState<Int, MovieResourceEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue { result is RemoteMediator.MediatorResult.Success }
        assertFalse { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        val remoteMediator = MoviesRemoteMediator(
            moviesDatabase = db,
            movieResourceDao = movieResourceDao,
            movieKeysDao = movieKeysDao,
            network = EmptyMoviesNetworkDataSource()
        )
        val pagingState = PagingState<Int, MovieResourceEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue { result is RemoteMediator.MediatorResult.Success }
        assertTrue { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        val remoteMediator = MoviesRemoteMediator(
            moviesDatabase = db,
            movieResourceDao = movieResourceDao,
            movieKeysDao = movieKeysDao,
            network = FailureMoviesNetworkDataSource()
        )
        val pagingState = PagingState<Int, MovieResourceEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue {result is RemoteMediator.MediatorResult.Error }
    }

    @After
    fun tearDown() {
        db.clearAllTables()
    }
}