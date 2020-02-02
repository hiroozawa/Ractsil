package com.hiroozawa.ractsil.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = setupNavController()

        with(viewDataBinding) {
            navView.setupWithNavController(navController)
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

