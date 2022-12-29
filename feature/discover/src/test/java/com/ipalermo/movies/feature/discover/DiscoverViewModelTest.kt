package com.ipalermo.movies.feature.discover

import androidx.paging.PagingData
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.testing.repository.TestMoviesRepository
import com.ipalermo.movies.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertIs

class DiscoverViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val moviesRepository = TestMoviesRepository()

    private lateinit var viewModel: DiscoverViewModel

    @Before
    fun setup() {
        viewModel = DiscoverViewModel(
            moviesRepository = moviesRepository
        )
    }

    @Test
    fun pagedMovies_matchesMoviesFromRepository() = runTest {
        launch(UnconfinedTestDispatcher()) {
            viewModel.pagedMovies.collect { pagedMovies ->

                assertIs<PagingData<MovieResource>>(pagedMovies)

                this.cancel()
            }
        }

        moviesRepository.sendMovieResources(sampleMovieResources)
    }
}

private val sampleMovieResources = listOf(
    MovieResource(
        id = 436270,
        title = "Black Adam",
        overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
        posterUrl = "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
        releaseDate = LocalDate.of(2022, 10, 19)
    ),
    MovieResource(
        id = 882598,
        title = "Thanks for helping us reach 1M YouTube Subscribers",
        overview = "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
        posterUrl = "/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
        releaseDate = LocalDate.of(2022, 9, 23),
    ),
    MovieResource(
        id = 882598,
        title = "Smile",
        overview = "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
        posterUrl = "/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
        releaseDate = LocalDate.parse("2022-09-23"),
    )
)
