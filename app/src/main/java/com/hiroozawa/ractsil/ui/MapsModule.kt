package com.hiroozawa.ractsil.ui

import androidx.lifecycle.ViewModel
import com.hiroozawa.ractsil.ui.MapsActivity
import com.hiroozawa.ractsil.ui.MapsActivityViewModel
import com.hiroozawa.ractsil.di.viewmodel.ViewModelBuilder
import com.hiroozawa.ractsil.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for maps feature
 */
@Module
abstract class MapsModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun addEMapsActivity(): MapsActivity

    @Binds
    @IntoMap
    @ViewModelKey(MapsActivityViewModel::class)
    internal abstract fun bindViewModel(viewmodel: MapsActivityViewModel): ViewModel
}
