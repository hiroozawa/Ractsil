package com.hiroozawa.ractsil.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.ui.MainActivityViewModel
import javax.inject.Inject

class MapFragment : Fragment() {

    private val viewModel by activityViewModels<MainActivityViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_map, container, false)
    }
}
