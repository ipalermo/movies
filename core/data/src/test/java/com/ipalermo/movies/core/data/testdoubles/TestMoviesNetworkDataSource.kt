package com.ipalermo.movies.core.data.testdoubles

import com.ipalermo.movies.core.network.MoviesNetworkDataSource
import com.ipalermo.movies.core.network.fake.FakeDataSource
import com.ipalermo.movies.core.network.model.NetworkMovieResource
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Test double for [MoviesNetworkDataSource]
 */
class TestMoviesNetworkDataSource : MoviesNetworkDataSource {

    private val networkJson = Json

    private val allMovieResources =
        networkJson.decodeFromString<List<NetworkMovieResource>>(FakeDataSource.data)

    override suspend fun getMovieResources(page:Int): List<NetworkMovieResource> = allMovieResources
}
