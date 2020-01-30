package com.hiroozawa.ractsil

import com.hiroozawa.ractsil.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * An [Application] that uses Dagger for Dependency Injection.
 *
 */
open class RactsilApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}
