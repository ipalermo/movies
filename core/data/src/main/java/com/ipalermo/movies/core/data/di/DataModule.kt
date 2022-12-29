package com.ipalermo.movies.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.ipalermo.movies.core.data.repository.MoviesRemoteMediator
import com.ipalermo.movies.core.data.repository.MoviesRepository
import com.ipalermo.movies.core.data.repository.OfflineFirstMoviesRepository
import com.ipalermo.movies.core.data.util.ConnectivityManagerNetworkMonitor
import com.ipalermo.movies.core.data.util.NetworkMonitor
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMoviesRepository(
        moviesRepository: OfflineFirstMoviesRepository
    ): MoviesRepository

    @OptIn(ExperimentalPagingApi::class)
    @Binds
    fun bindsRemoteMediator(
        moviesRemoteMediator: MoviesRemoteMediator
    ): RemoteMediator<Int, MovieResourceEntity>

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}
