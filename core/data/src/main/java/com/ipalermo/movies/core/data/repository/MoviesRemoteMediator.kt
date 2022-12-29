package com.ipalermo.movies.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ipalermo.movies.core.data.model.asEntity
import com.ipalermo.movies.core.database.MoviesDatabase
import com.ipalermo.movies.core.database.dao.MovieKeysDao
import com.ipalermo.movies.core.database.dao.MovieResourceDao
import com.ipalermo.movies.core.database.model.MovieKeysEntity
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.network.MoviesNetworkDataSource
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val movieResourceDao: MovieResourceDao,
    private val movieKeysDao: MovieKeysDao,
    private val network: MoviesNetworkDataSource,
) : RemoteMediator<Int, MovieResourceEntity>() {

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MovieResourceEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: TMDB_STARTING_PAGE_INDEX
                }
                PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with `endOfPaginationReached = false` because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                    // the end of pagination for prepend.
                    val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevKey
                }
                APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with `endOfPaginationReached = false` because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.
                    val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextKey
                }
            }

            val networkMovies = network.getMovieResources(page)
            val endOfPaginationReached = networkMovies.isEmpty()

            val prevKey = if (page == TMDB_STARTING_PAGE_INDEX) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1

            moviesDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == REFRESH) {
                    movieKeysDao.clearKeys()
                    movieResourceDao.deleteMovieResources()
                }

                val keys = networkMovies.map {
                    MovieKeysEntity(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                val movies = networkMovies.map {
                    it.asEntity()
                }
                movieKeysDao.insertAll(keys)
                movieResourceDao.insertAll(movies)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieResourceEntity>): MovieKeysEntity? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movie ->
            // Get the remote keys of the last item retrieved
            movieKeysDao.keysMovieId(movie.movieId)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieResourceEntity>): MovieKeysEntity? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movie ->
            // Get the remote keys of the first items retrieved
            movieKeysDao.keysMovieId(movie.movieId)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieResourceEntity>
    ): MovieKeysEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { movieId ->
                movieKeysDao.keysMovieId(movieId)
            }
        }
    }
}

private const val TMDB_STARTING_PAGE_INDEX = 1
