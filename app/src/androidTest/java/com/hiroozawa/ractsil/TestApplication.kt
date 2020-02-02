package com.hiroozawa.ractsil

import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class TestRactsilApplication : RactsilApplication() {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
}
