package com.ipalermo.movies.core.network.di

import com.ipalermo.movies.core.network.MoviesNetworkDataSource
import com.ipalermo.movies.core.network.fake.FakeMoviesNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsMoviesNetwork(
        moviesNetwork: FakeMoviesNetworkDataSource
    ): MoviesNetworkDataSource

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}
