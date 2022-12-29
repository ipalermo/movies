package com.ipalermo.movies.core.database.model

import com.ipalermo.movies.core.model.data.MovieResource
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class MovieResourceEntityKtTest {
    @Test
    fun populated_movie_entity_can_be_mapped_to_movie_resource() {
        val movieResourceEntity = MovieResourceEntity(
            movieId = 12345,
            title = "Title",
            overview = "overview",
            posterUrl = "https://image.example.org/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
            releaseDate = LocalDate.of(2022, 1, 1)
        )
        val movieResource = movieResourceEntity.asExternalModel()

        assertEquals(
            MovieResource(
                id = 12345,
                title = "Title",
                overview = "overview",
                posterUrl = "https://image.example.org/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
                releaseDate = LocalDate.of(2022, 1, 1),
            ),
            movieResource
        )
    }
}
