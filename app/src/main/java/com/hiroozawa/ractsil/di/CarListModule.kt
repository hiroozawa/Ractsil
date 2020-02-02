package com.hiroozawa.ractsil.di

import androidx.lifecycle.ViewModel
import com.hiroozawa.ractsil.di.viewmodel.ViewModelBuilder
import com.hiroozawa.ractsil.di.viewmodel.ViewModelKey
import com.hiroozawa.ractsil.ui.list.CarListFragment
import com.hiroozawa.ractsil.ui.list.CarListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for main activity
 */
@Module
abstract class CarListModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun carListFragment(): CarListFragment

    @Binds
    @IntoMap
    @ViewModelKey(CarListViewModel::class)
    internal abstract fun bindViewModel(viewModel: CarListViewModel): ViewModel
}
