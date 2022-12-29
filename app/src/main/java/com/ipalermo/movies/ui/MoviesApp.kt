package com.ipalermo.movies.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ipalermo.movies.R
import com.ipalermo.movies.core.data.util.NetworkMonitor
import com.ipalermo.movies.core.designsystem.component.MoviesGradientBackground
import com.ipalermo.movies.core.designsystem.component.MoviesTopAppBar
import com.ipalermo.movies.core.designsystem.icon.MoviesIcons
import com.ipalermo.movies.navigation.MoviesNavHost

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun MoviesApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    appState: MoviesAppState = rememberMoviesAppState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass
    ),
) {
    MoviesGradientBackground {

        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                // Show the top app bar on top level destinations.
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    MoviesTopAppBar(
                        titleRes = destination.titleTextId,
                        actionIcon = MoviesIcons.Person,
                        actionIconContentDescription = stringResource(R.string.settings),
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent
                        ),
                        onActionClick = { }
                    )
                }
            }
        ) { padding ->

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            // If user is not connected to the internet show a snack bar to inform them.
            val notConnected = stringResource(R.string.not_connected)
            LaunchedEffect(isOffline) {
                if (isOffline) snackbarHostState.showSnackbar(
                    message = notConnected,
                    duration = Indefinite
                )
            }

            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                MoviesNavHost(
                    navController = appState.navController,
                    onBackClick = appState::onBackClick,

                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding)
                )
            }
        }
    }
}
