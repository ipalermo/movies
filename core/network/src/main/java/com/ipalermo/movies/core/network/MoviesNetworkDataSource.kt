package com.ipalermo.movies.core.network

import com.ipalermo.movies.core.network.model.NetworkMovieResource

/**
 * Interface representing network calls to the tmdb api
 */
interface MoviesNetworkDataSource {

    suspend fun getMovieResources(page: Int): List<NetworkMovieResource>

}
