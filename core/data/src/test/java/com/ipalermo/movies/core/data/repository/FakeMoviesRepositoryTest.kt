package com.ipalermo.movies.core.data.repository

import com.ipalermo.movies.core.data.repository.fake.FakeMoviesRepository
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.serialization.json.Json
import org.junit.Before

class FakeMoviesRepositoryTest {

    private lateinit var subject: FakeMoviesRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        subject = FakeMoviesRepository(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true }
        )
    }
}
