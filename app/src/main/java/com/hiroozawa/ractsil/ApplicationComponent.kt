package com.hiroozawa.ractsil

import android.content.Context
import com.hiroozawa.ractsil.remote.RemoteDataModule
import com.hiroozawa.ractsil.viewmodel.ViewModelBuilder
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
        RemoteDataModule::class,
        ViewModelBuilder::class,
        AndroidInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<RactsilApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
