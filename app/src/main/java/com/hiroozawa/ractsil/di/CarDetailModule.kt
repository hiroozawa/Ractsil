package com.hiroozawa.ractsil.di

import androidx.lifecycle.ViewModel
import com.hiroozawa.ractsil.di.viewmodel.ViewModelBuilder
import com.hiroozawa.ractsil.di.viewmodel.ViewModelKey
import com.hiroozawa.ractsil.ui.detail.CarDetailDialogFragment
import com.hiroozawa.ractsil.ui.detail.CarDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CarDetailModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun carDetailFragment(): CarDetailDialogFragment

    @Binds
    @IntoMap
    @ViewModelKey(CarDetailViewModel::class)
    internal abstract fun bindViewModel(viewModel: CarDetailViewModel): ViewModel
}
