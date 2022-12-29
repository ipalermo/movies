package com.ipalermo.movies.core.testing.di

import com.ipalermo.movies.core.decoder.StringDecoder
import com.ipalermo.movies.core.decoder.di.StringDecoderModule
import com.ipalermo.movies.core.testing.decoder.FakeStringDecoder
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [StringDecoderModule::class],
)
abstract class TestStringDecoderModule {
    @Binds
    abstract fun bindsStringDecoder(fakeStringDecoder: FakeStringDecoder): StringDecoder
}
