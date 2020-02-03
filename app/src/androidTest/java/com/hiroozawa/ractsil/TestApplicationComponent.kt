package com.hiroozawa.ractsil

import android.content.Context
import com.hiroozawa.ractsil.data.remote.RemoteDataModule
import com.hiroozawa.ractsil.di.CarListModule
import com.hiroozawa.ractsil.di.CarMapModule
import com.hiroozawa.ractsil.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Main component for the application.
 */
@Singleton
@Component(
    modules = [
        FakeRemoteConfigModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        CarListModule::class,
        CarMapModule::class,
        AndroidInjectionModule::class
    ]
)
interface TestApplicationComponent : AndroidInjector<TestRactsilApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestApplicationComponent
    }
}
