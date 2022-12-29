package com.ipalermo.movies.feature.movie

import androidx.lifecycle.SavedStateHandle
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.testing.repository.TestMoviesRepository
import com.ipalermo.movies.core.testing.util.MainDispatcherRule
import com.ipalermo.movies.feature.movie.navigation.movieIdArg
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

class MovieViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val moviesRepository = TestMoviesRepository()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    movieIdArg to testInputMovies[0].id
                )
            ),
            moviesRepository = moviesRepository
        )
    }

    @Test
    fun uiStateMovie_whenSuccess_matchesMovieFromRepository() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.movieUiState.collect() }

        // To make sure MovieUiState is success
        moviesRepository.sendMovieResources(testInputMovies)

        val item = viewModel.movieUiState.value
        assertIs<MovieUiState.Success>(item)

        val movieFromRepository = moviesRepository.getMovieStream(
            id = testInputMovies[0].id
        ).first()

        assertEquals(movieFromRepository, item.movieResource)

        collectJob.cancel()
    }

    @Test
    fun uiStateMovie_whenInitialized_thenShowLoading() = runTest {
        assertEquals(MovieUiState.Loading, viewModel.movieUiState.value)
    }

    @Test
    fun uiStateMovie_whenMovieSuccess_thenMovieSuccess() =
        runTest {
            val collectJob = launch(UnconfinedTestDispatcher()) {
                viewModel.movieUiState.collect()
            }

            moviesRepository.sendMovieResources(testInputMovies)
            val movieState = viewModel.movieUiState.value

            assertIs<MovieUiState.Success>(movieState)

            collectJob.cancel()
        }
}

private val testInputMovies = listOf(
    MovieResource(
        id = 0,
        title = "Black Adam",
        overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
        posterUrl = "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
        releaseDate = LocalDate.of(2022, 10, 19)
    ),
    MovieResource(
        id = 1,
        title = "Thanks for helping us reach 1M YouTube Subscribers",
        overview = "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
        posterUrl = "/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
        releaseDate = LocalDate.of(2022,9, 23),
    ),
    MovieResource(
        id = 2,
        title = "Smile",
        overview = "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
        posterUrl = "/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
        releaseDate = LocalDate.parse("2022-09-23"),
    )
)
