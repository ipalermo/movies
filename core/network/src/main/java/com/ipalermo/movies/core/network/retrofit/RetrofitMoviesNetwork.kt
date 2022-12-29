package com.ipalermo.movies.core.network.retrofit

import com.ipalermo.movies.core.network.BuildConfig
import com.ipalermo.movies.core.network.MoviesNetworkDataSource
import com.ipalermo.movies.core.network.model.NetworkMovieResource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

/**
 * Retrofit API declaration for Tmdb API
 */
private interface RetrofitMoviesNetworkApi {
    @GET(value = "discover/movie")
    suspend fun getMovieResources(
        @Query("page") page: Int
    ): NetworkResponse<NetworkMovieResource>
}

private const val TmdbBaseUrl = BuildConfig.TMDB_BASE_URL
private const val TmdbBearer = BuildConfig.TMDB_BEARER

/**
 * Wrapper for data provided from the [TmdbBaseUrl]
 */
@Serializable
private data class NetworkResponse<T>(
    val page: Int,
    val results: List<T>
)

/**
 * [Retrofit] backed [MoviesNetworkDataSource]
 */
@Singleton
class RetrofitMoviesNetwork @Inject constructor(
    networkJson: Json
) : MoviesNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(TmdbBaseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    AuthHeaderInterceptor()
                )
                .addInterceptor(
                    // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(RetrofitMoviesNetworkApi::class.java)



    override suspend fun getMovieResources(page: Int): List<NetworkMovieResource> =
        networkApi.getMovieResources(page = page).results
}

private class AuthHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("Authorization", "Bearer $TmdbBearer")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
