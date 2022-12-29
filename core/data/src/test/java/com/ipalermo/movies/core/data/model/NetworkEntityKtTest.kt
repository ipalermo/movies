package com.ipalermo.movies.core.data.model

import com.ipalermo.movies.core.network.BuildConfig
import com.ipalermo.movies.core.network.model.NetworkMovieResource
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class NetworkEntityKtTest {

    @Test
    fun network_movie_can_be_mapped_to_movie_entity() {
        val networkModel = NetworkMovieResource(
            id = 12345,
            title = "Movie",
            overview = "overview",
            posterPath = "/posterPath",
            releaseDate = LocalDate.of(2022, 1, 1),
        )
        val entity = networkModel.asEntity()

        assertEquals(networkModel.id, entity.movieId)
        assertEquals(networkModel.title, entity.title)
        assertEquals(networkModel.overview, entity.overview)
        assertEquals(
            BuildConfig.TMDB_IMAGE_BASE_URL + POSTER_PATH_WIDTH + "/posterPath",
            entity.posterUrl
        )
        assertEquals(networkModel.releaseDate, entity.releaseDate)
    }
}
