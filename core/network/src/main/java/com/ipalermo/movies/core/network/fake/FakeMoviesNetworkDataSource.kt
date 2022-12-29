package com.ipalermo.movies.core.network.fake

import com.ipalermo.movies.core.network.Dispatcher
import com.ipalermo.movies.core.network.MoviesNetworkDataSource
import com.ipalermo.movies.core.network.MoviesDispatchers.IO
import com.ipalermo.movies.core.network.model.NetworkMovieResource
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.LocalDate

/**
 * [MoviesNetworkDataSource] implementation that provides static movie resources to aid development
 */
class FakeMoviesNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json
) : MoviesNetworkDataSource {

    override suspend fun getMovieResources(page: Int): List<NetworkMovieResource> =
        withContext(ioDispatcher) {
            networkJson.decodeFromString(FakeDataSource.data)
        }
}

class SuccessMoviesNetworkDataSource : MoviesNetworkDataSource {

    override suspend fun getMovieResources(page: Int): List<NetworkMovieResource> =
       listOf(
           NetworkMovieResource(
               id = 12345,
               title = "Movie",
               overview = "overview",
               posterPath = "/posterPath",
               releaseDate = LocalDate.of(2022, 1, 1),
           )
       )
}

class EmptyMoviesNetworkDataSource : MoviesNetworkDataSource {

    override suspend fun getMovieResources(page: Int): List<NetworkMovieResource> =
        emptyList()
}

class FailureMoviesNetworkDataSource : MoviesNetworkDataSource {

    override suspend fun getMovieResources(page: Int): List<NetworkMovieResource> =
        throw Exception()
}