package com.ipalermo.movies.feature.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ipalermo.movies.core.designsystem.component.MoviesOverlayLoadingWheel
import com.ipalermo.movies.core.designsystem.theme.MoviesTheme
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.model.data.previewMovieResources
import com.ipalermo.movies.core.ui.DevicePreviews
import com.ipalermo.movies.core.ui.MovieCard
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun DiscoverRoute(
    navigateToMovie: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel()
) {
    val items = viewModel.pagedMovies.collectAsLazyPagingItems()
    DiscoverScreen(
        lazyPagingItems = items,
        navigateToMovie = navigateToMovie,
        modifier = modifier
    )
}

@Composable
internal fun DiscoverScreen(
    lazyPagingItems: LazyPagingItems<MovieResource>,
    navigateToMovie: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyGridState()

    LazyVerticalGrid(
        columns = Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("discover:feed"),
        state = state
    ) {
        moviesFeed(
            lazyPagingItems = lazyPagingItems,
            navigateToMovie = navigateToMovie
        )

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                // Add space for the content to clear the "offline" snackbar.
                // if (isOffline) Spacer(modifier = Modifier.height(48.dp))
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        }
    }

    AnimatedVisibility(
        visible = lazyPagingItems.loadState.refresh == LoadState.Loading,
        exit = fadeOut(),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            MoviesOverlayLoadingWheel(
                modifier = Modifier.align(Alignment.Center),
                contentDesc = stringResource(id = R.string.discover_loading)
            )
        }
    }
}

/**
 * An extension on [LazyListScope] defining a feed with movie resources.
 */
fun LazyGridScope.moviesFeed(
    lazyPagingItems: LazyPagingItems<MovieResource>,
    navigateToMovie: (Long) -> Unit,
) {
    if (lazyPagingItems.loadState.refresh !is LoadState.Loading) {
        items(
            count = lazyPagingItems.itemCount,
        ) { index ->
            lazyPagingItems[index]?.let { movieResource ->
                MovieCard(
                    movieResource = movieResource,
                    onClick = { navigateToMovie(movieResource.id) },
                )
            }
        }
    }
}

@DevicePreviews
@Composable
fun DiscoverScreenPopulatedFeed() {
    BoxWithConstraints {
        MoviesTheme {
            DiscoverScreen(
                lazyPagingItems = flowOf(
                    PagingData.from(previewMovieResources)
                ).collectAsLazyPagingItems(),
                navigateToMovie = {}
            )
        }
    }
}

@DevicePreviews
@Composable
fun DiscoverScreenOfflinePopulatedFeed() {
    BoxWithConstraints {
        MoviesTheme {
            DiscoverScreen(
                lazyPagingItems = flowOf(
                    PagingData.from(previewMovieResources)
                ).collectAsLazyPagingItems(),
                navigateToMovie = {}
            )
        }
    }
}

@DevicePreviews
@Composable
fun DiscoverScreenLoading() {
    BoxWithConstraints {
        MoviesTheme {
            DiscoverScreen(
                lazyPagingItems = flowOf<PagingData<MovieResource>>(
                    PagingData.empty()
                ).collectAsLazyPagingItems(),
                navigateToMovie = {}
            )
        }
    }
}

@DevicePreviews
@Composable
fun DiscoverScreenPopulatedAndLoading() {
    BoxWithConstraints {
        MoviesTheme {
            DiscoverScreen(
                lazyPagingItems = flowOf(
                    PagingData.from(previewMovieResources)
                ).collectAsLazyPagingItems(),
                navigateToMovie = {}
            )
        }
    }
}
