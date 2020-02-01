package com.hiroozawa.ractsil.di

import androidx.lifecycle.ViewModel
import com.hiroozawa.ractsil.di.viewmodel.ViewModelBuilder
import com.hiroozawa.ractsil.di.viewmodel.ViewModelKey
import com.hiroozawa.ractsil.ui.MainActivity
import com.hiroozawa.ractsil.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for main activity
 */
@Module
abstract class RactsilModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun addMapsActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindViewModel(viewModel: MainActivityViewModel): ViewModel
}
