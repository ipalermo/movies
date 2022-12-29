package com.ipalermo.movies.core.data.testdoubles

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.ipalermo.movies.core.database.model.MovieResourceEntity

/**
 * Test double for [TestMoviesRemoteMediator]
 */
@OptIn(ExperimentalPagingApi::class)
class TestMoviesRemoteMediator : RemoteMediator<Int, MovieResourceEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieResourceEntity>
    ): MediatorResult {
        return MediatorResult.Success(endOfPaginationReached = false)
    }
}
