package com.ipalermo.movies.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ipalermo.movies.core.designsystem.R as DesignsystemR
import coil.compose.AsyncImage
import com.ipalermo.movies.core.designsystem.theme.MoviesTheme
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.model.data.previewMovieResources

/**
 * [MovieResource] card used on the following screens
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movieResource: MovieResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickActionLabel = stringResource(R.string.card_tap_action)
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Use custom label for accessibility services to communicate button's action to user.
        // Pass null for action to only override the label and not the actual action.
        modifier = modifier.defaultMinSize(minHeight = 200.dp)
            .semantics {
            onClick(label = clickActionLabel, action = null)
        }
    ) {
        Column {
            MovieResourceHeaderImage(
                movieResource.posterUrl,
            )
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                MovieResourceTitle(
                    movieResource.title,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun MovieResourceHeaderImage(
    posterUrl: String?
) {
    AsyncImage(
        placeholder = if (LocalInspectionMode.current) {
            painterResource(DesignsystemR.drawable.ic_bookmark)
        } else {
            // TODO b/228077205, show specific loading image visual
            null
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Crop,
        model = posterUrl,
        contentDescription = null
    )
}

@Composable
fun MovieResourceTitle(
    movieResourceTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = movieResourceTitle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Preview("MovieResourceCardExpanded")
@Composable
fun ExpandedMovieResourcePreview() {
    MoviesTheme {
        Surface {
            MovieCard(
                movieResource = previewMovieResources[0],
                onClick = {}
            )
        }
    }
}
