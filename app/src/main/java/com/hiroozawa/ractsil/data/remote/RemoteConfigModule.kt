package com.hiroozawa.ractsil.data.remote

import com.hiroozawa.ractsil.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object RemoteConfigModule {

    @Named("SERVER_URL")
    @Provides
    fun provideServerUrl(): String = BuildConfig.SERVER_URL
}

