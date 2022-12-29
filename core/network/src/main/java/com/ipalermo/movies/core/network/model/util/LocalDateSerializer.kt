package com.ipalermo.movies.core.network.model.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object LocalDateSerializer : KSerializer<LocalDate> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "LocalDate",
        kind = STRING
    )

    override fun serialize(encoder: Encoder, value: LocalDate) = encoder.encodeString(
        value.format(
            DateTimeFormatter.ofPattern("yyyy-MM-DD").withLocale(Locale.getDefault())
        )
    )

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}
