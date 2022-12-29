package com.ipalermo.movies.core.database

import com.ipalermo.movies.core.database.dao.MovieKeysDao
import com.ipalermo.movies.core.database.dao.MovieResourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesMovieResourceDao(
        database: MoviesDatabase,
    ): MovieResourceDao = database.movieResourceDao()

    @Provides
    fun providesMovieKeysDao(
        database: MoviesDatabase,
    ): MovieKeysDao = database.movieKeysDao()
}
