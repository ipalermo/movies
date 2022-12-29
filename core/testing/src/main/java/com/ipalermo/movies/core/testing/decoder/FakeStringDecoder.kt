package com.ipalermo.movies.core.testing.decoder

import com.ipalermo.movies.core.decoder.StringDecoder
import javax.inject.Inject

class FakeStringDecoder @Inject constructor() : StringDecoder {
    override fun decodeString(encodedString: String): String = encodedString
}
