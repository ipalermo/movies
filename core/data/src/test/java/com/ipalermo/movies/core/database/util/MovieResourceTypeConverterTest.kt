package com.ipalermo.movies.core.database.util

import kotlin.test.assertEquals
import org.junit.Test
import java.time.LocalDate

class LocalDateConverterTest {

    @Test
    fun test_room_string_date_converter_to_local_date() {
        assertEquals(
            LocalDate.of(2022, 11, 25),
            LocalDateConverter().fromTimestamp("2022-11-25")
        )
    }

    @Test
    fun test_room_local_date_converter_to_string_date() {
        assertEquals(
            "2022-11-25",
            LocalDateConverter().dateToTimestamp(LocalDate.of(2022, 11, 25))
        )
    }
}
