package com.hiroozawa.ractsil

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object FakeRemoteConfigModule {
    private const val FAKE_SERVER_URL = "http://127.0.0.1:8080"

    @Named("SERVER_URL")
    @Provides
    fun provideFakeServerUrl(): String = FAKE_SERVER_URL
}