package com.ipalermo.movies.core.data.repository.fake

import androidx.paging.PagingData
import com.ipalermo.movies.core.data.model.asEntity
import com.ipalermo.movies.core.data.repository.MoviesRepository
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.database.model.asExternalModel
import com.ipalermo.movies.core.model.data.MovieResource
import com.ipalermo.movies.core.network.Dispatcher
import com.ipalermo.movies.core.network.MoviesDispatchers.IO
import com.ipalermo.movies.core.network.fake.FakeDataSource
import com.ipalermo.movies.core.network.model.NetworkMovieResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Fake implementation of the [MoviesRepository] that retrieves the movie resources from a JSON String.
 *
 * This allows us to run the app with fake data, without needing an internet connection or working
 * backend.
 */
class FakeMoviesRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json
) : MoviesRepository {

    override fun getMovieResourcesStream(): Flow<PagingData<MovieResource>> =
        flow {
            emit(
                PagingData.from(
                    networkJson.decodeFromString<List<NetworkMovieResource>>(FakeDataSource.data)
                        .map(NetworkMovieResource::asEntity)
                        .map(MovieResourceEntity::asExternalModel)
                )
            )
        }
            .flowOn(ioDispatcher)

    override fun getMovieStream(id: Long): Flow<MovieResource> =
        flow {
            networkJson.decodeFromString<List<NetworkMovieResource>>(FakeDataSource.data)
                .find { it.id == id}
                ?.asEntity()
                ?.asExternalModel()
                ?.let { emit (it) }
        }
}
