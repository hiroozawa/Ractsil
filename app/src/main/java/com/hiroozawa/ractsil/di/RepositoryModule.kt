package com.hiroozawa.ractsil.di

import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.DefaultCarRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule{
    @Binds
    abstract fun bindRepository(defaultCarRepository: DefaultCarRepository): CarRepository
}