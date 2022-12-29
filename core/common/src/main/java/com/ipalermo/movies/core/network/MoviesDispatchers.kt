package com.ipalermo.movies.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val moviesDispatcher: MoviesDispatchers)

enum class MoviesDispatchers {
    IO
}
