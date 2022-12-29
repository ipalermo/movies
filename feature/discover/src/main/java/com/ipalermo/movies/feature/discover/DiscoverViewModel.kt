package com.ipalermo.movies.feature.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ipalermo.movies.core.data.repository.MoviesRepository
import com.ipalermo.movies.core.model.data.MovieResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {

    val pagedMovies: Flow<PagingData<MovieResource>> = moviesRepository.getMovieResourcesStream()
        .cachedIn(viewModelScope)
}