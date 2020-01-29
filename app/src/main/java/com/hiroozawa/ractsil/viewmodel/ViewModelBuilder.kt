package com.hiroozawa.ractsil.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: RactsilViewModelFactory
    ): ViewModelProvider.Factory
}