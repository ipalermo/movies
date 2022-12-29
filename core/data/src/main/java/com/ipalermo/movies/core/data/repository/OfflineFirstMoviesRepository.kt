package com.ipalermo.movies.core.data.repository

import androidx.paging.*
import com.ipalermo.movies.core.database.dao.MovieResourceDao
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.database.model.asExternalModel
import com.ipalermo.movies.core.model.data.MovieResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Disk storage backed implementation of the [MoviesRepository].
 * Reads are exclusively from local storage to support offline access.
 */
@OptIn(ExperimentalPagingApi::class)
class OfflineFirstMoviesRepository @Inject constructor(
    private val movieResourceDao: MovieResourceDao,
    mediator: RemoteMediator<Int, MovieResourceEntity>
) : MoviesRepository {

    private val moviesPager = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        remoteMediator = mediator,
        pagingSourceFactory = { movieResourceDao.getMovieResourcesPagingSource() }
    )

    override fun getMovieResourcesStream(): Flow<PagingData<MovieResource>> =
        moviesPager.flow.map { pagingData ->
            pagingData.map(MovieResourceEntity::asExternalModel)
        }

    override fun getMovieStream(id: Long): Flow<MovieResource> =
        movieResourceDao.getMovieResourceEntityStream(
            movieId = id
        ).map {
            it.asExternalModel()
        }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
