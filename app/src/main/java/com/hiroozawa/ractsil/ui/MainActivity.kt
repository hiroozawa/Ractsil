package com.hiroozawa.ractsil.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hiroozawa.ractsil.R
import dagger.android.DaggerActivity
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = viewModelFactory.create(MainActivityViewModel::class.java)

        viewModel.load()
    }


}
