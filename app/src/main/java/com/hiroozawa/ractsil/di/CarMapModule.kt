package com.hiroozawa.ractsil.di

import androidx.lifecycle.ViewModel
import com.hiroozawa.ractsil.di.viewmodel.ViewModelBuilder
import com.hiroozawa.ractsil.di.viewmodel.ViewModelKey
import com.hiroozawa.ractsil.ui.map.CarMapFragment
import com.hiroozawa.ractsil.ui.map.CarMapViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CarMapModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun mapFragment(): CarMapFragment

    @Binds
    @IntoMap
    @ViewModelKey(CarMapViewModel::class)
    internal abstract fun bindViewModel(viewModel: CarMapViewModel): ViewModel
}
