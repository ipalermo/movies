package com.ipalermo.movies.core.network.fake

import kotlin.test.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class FakeMoviesNetworkDataSourceTest {

    private lateinit var subject: FakeMoviesNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = FakeMoviesNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true }
        )
    }

    @Test
    fun testDeserializationOfMovieResources() = runTest(testDispatcher) {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getMovieResources(1).find { it.id == FakeDataSource.sampleResource.id }
        )
    }
}
