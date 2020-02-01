package com.hiroozawa.ractsil.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.databinding.ActivityMainBinding
import com.hiroozawa.ractsil.ui.util.setupSnackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = setupNavController()

        with(viewDataBinding) {
            navView.setupWithNavController(navController)
            root.setupSnackbar(this@MainActivity, viewModel.errorLabel, Snackbar.LENGTH_LONG)
        }
    }

    private fun setupNavController(): NavController {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_list, R.id.navigation_map
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        return navController
    }
}

