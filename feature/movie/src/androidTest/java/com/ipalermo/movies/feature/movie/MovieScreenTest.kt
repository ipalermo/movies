package com.ipalermo.movies.feature.movie

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.ipalermo.movies.core.model.data.MovieResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

/**
 * UI test for checking the correct behaviour of the Movie screen;
 * Verifies that, when a specific UiState is set, the corresponding
 * composables and details are shown
 */
class MovieScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var movieLoading: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            movieLoading = getString(R.string.movie_loading)
        }
    }

    @Test
    fun loadingWheel_whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            MovieScreen(
                movieUiState = MovieUiState.Loading,
                onBackClick = { }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(movieLoading)
            .assertExists()
    }

    @Test
    fun movieTitle_whenMovieIsSuccess_isShown() {
        val testMovie = testMovies.first()
        composeTestRule.setContent {
            MovieScreen(
                movieUiState = MovieUiState.Success(testMovie),
                onBackClick = { }
            )
        }

        // Title is shown
        composeTestRule
            .onNodeWithText(testMovie.title)
            .assertExists()

        // Overview is shown
        composeTestRule
            .onNodeWithText(testMovie.overview)
            .assertExists()
    }
}

private val testMovies = listOf(
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
