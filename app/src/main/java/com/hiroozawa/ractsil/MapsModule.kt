package com.hiroozawa.ractsil

import androidx.lifecycle.ViewModel
import com.hiroozawa.ractsil.viewmodel.ViewModelBuilder
import com.hiroozawa.ractsil.viewmodel.ViewModelKey
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
