package com.hiroozawa.ractsil.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hiroozawa.ractsil.R
import dagger.android.DaggerActivity
import javax.inject.Inject

class MapsActivity : DaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val viewModel = viewModelFactory.create(MapsActivityViewModel::class.java)

        viewModel.load()
    }


}
