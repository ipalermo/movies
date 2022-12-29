package com.ipalermo.movies.feature.discover

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.model.data.previewMovieResources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class DiscoverScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val items = flowOf(PagingData.from(previewMovieResources))
    private val empty: Flow<PagingData<MovieResource>> = flowOf(PagingData.empty())

    @Test
    fun circularProgressIndicator_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            BoxWithConstraints {
                DiscoverScreen(
                    lazyPagingItems = empty.collectAsLazyPagingItems(),
                    navigateToMovie = {}
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(
                composeTestRule.activity.resources.getString(R.string.discover_loading)
            )
            .assertExists()
    }
    
    @Test
    fun feed_whenItemsLoaded_showsFeed() {
        composeTestRule.setContent {
            val items = items.collectAsLazyPagingItems()
            DiscoverScreen(
                lazyPagingItems = items,
                navigateToMovie = {}
            )
        }

        composeTestRule
            .onNodeWithText(
                previewMovieResources[0].title,
                ignoreCase = true,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    previewMovieResources[1].title,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                previewMovieResources[1].title,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
    }
}
