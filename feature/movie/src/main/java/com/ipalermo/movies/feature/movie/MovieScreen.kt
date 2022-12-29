package com.ipalermo.movies.feature.movie

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ipalermo.movies.core.designsystem.component.MoviesBackground
import com.ipalermo.movies.core.designsystem.component.MoviesLoadingWheel
import com.ipalermo.movies.core.designsystem.component.MoviesTextButton
import com.ipalermo.movies.core.designsystem.theme.LocalBackgroundTheme
import com.ipalermo.movies.core.designsystem.theme.MoviesTheme
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.model.data.previewMovieResources
import com.ipalermo.movies.core.ui.DevicePreviews
import com.ipalermo.movies.core.ui.TrackScrollJank

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MovieRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val movieUiState: MovieUiState by viewModel.movieUiState.collectAsStateWithLifecycle()

    MovieScreen(
        movieUiState = movieUiState,
        modifier = modifier
            .fillMaxSize()
            .background(color = colorScheme.background),
        onBackClick = onBackClick
    )
}

@VisibleForTesting
@Composable
internal fun MovieScreen(
    movieUiState: MovieUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollableState = rememberLazyListState()
    TrackScrollJank(scrollableState = scrollableState, stateName = "movie:column")
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollableState
    ) {
        when (movieUiState) {
            MovieUiState.Loading ->
                item {
                    MoviesLoadingWheel(
                        modifier = modifier,
                        contentDesc = stringResource(id = R.string.movie_loading),
                    )
                }
            MovieUiState.Error -> {
                TODO()
            }
            is MovieUiState.Success ->
                movieContent(
                    movieResource = movieUiState.movieResource,
                    onBackClick = onBackClick
                )
        }
        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}

private fun LazyListScope.movieContent(
    movieResource: MovieResource,
    onBackClick: () -> Unit
) {
    item {
        MovieHeader(
            posterUrl = movieResource.posterUrl,
            title = movieResource.title,
            onBackClick = onBackClick
        )
    }
    item {
        MovieBody(movieResource)
    }
}

@Composable
private fun MovieHeader(
    posterUrl: String?,
    title: String,
    onBackClick: () -> Unit,
    bgColor: Color = LocalBackgroundTheme.current.color
) {
    Box {
        AsyncImage(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .height(400.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .drawWithCache {
                    onDrawWithContent {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                bgColor
                            ),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        drawContent()
                        drawRect(
                            brush = gradient,
                            blendMode = BlendMode.SrcOver
                        )
                    }
                },
            contentScale = ContentScale.Crop,
            model = posterUrl,
            contentDescription = "Movie poster",
        )
        Column {
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
            MovieToolbar(onBackClick = onBackClick)
        }
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 24.dp)
                .padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun MovieBody(movie: MovieResource) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }
        var showMore by remember { mutableStateOf(false) }
        Text(
            text = movie.overview,
            modifier = Modifier
                .padding(top = 24.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            maxLines = if (!expanded) 2 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { if (it.hasVisualOverflow) showMore = true },
            style = MaterialTheme.typography.bodyLarge
        )
        if (showMore) {
            MoviesTextButton(
                modifier = Modifier.align(End),
                onClick = { expanded = !expanded }
            ) {
                Text(
                    text = stringResource(if (expanded) R.string.less else R.string.more),
                )
            }
        }
    }
}

@Composable
private fun MovieToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Filled.ArrowBack,
                contentDescription = stringResource(
                    id = com.ipalermo.movies.core.ui.R.string.back
                )
            )
        }
    }
}

@DevicePreviews
@Composable
fun MovieScreenPopulated() {
    MoviesTheme {
        MoviesBackground {
            MovieScreen(
                movieUiState = MovieUiState.Success(previewMovieResources[0]),
                onBackClick = {}
            )
        }
    }
}

@DevicePreviews
@Composable
fun MovieScreenLoading() {
    MoviesTheme {
        MoviesBackground {
            MovieScreen(
                movieUiState = MovieUiState.Loading,
                onBackClick = {}
            )
        }
    }
}
