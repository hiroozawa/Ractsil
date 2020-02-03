package com.hiroozawa.ractsil.di

import android.content.Context
import com.hiroozawa.ractsil.RactsilApplication
import com.hiroozawa.ractsil.data.remote.RemoteConfigModule
import com.hiroozawa.ractsil.data.remote.RemoteDataModule
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
        RemoteConfigModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        CarListModule::class,
        CarMapModule::class,
        CarDetailModule::class,
        AndroidInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<RactsilApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
