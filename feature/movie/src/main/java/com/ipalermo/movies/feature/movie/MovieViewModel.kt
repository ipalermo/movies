package com.ipalermo.movies.feature.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipalermo.movies.core.data.repository.MoviesRepository
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.result.Result
import com.ipalermo.movies.core.result.asResult
import com.ipalermo.movies.feature.movie.navigation.MovieArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    moviesRepository: MoviesRepository,
) : ViewModel() {

    private val movieArgs: MovieArgs = MovieArgs(savedStateHandle)

    val movieUiState = moviesRepository.getMovieStream(id = movieArgs.movieId)
        .asResult()
        .map { movieResult ->
            when (movieResult) {
                is Result.Success -> MovieUiState.Success(movieResult.data)
                is Result.Loading -> MovieUiState.Loading
                is Result.Error -> MovieUiState.Error
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieUiState.Loading
        )
}

sealed interface MovieUiState {
    data class Success(val movieResource: MovieResource) : MovieUiState
    object Error : MovieUiState
    object Loading : MovieUiState
}
