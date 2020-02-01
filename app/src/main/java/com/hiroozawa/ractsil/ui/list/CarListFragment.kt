package com.hiroozawa.ractsil.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hiroozawa.ractsil.databinding.FragmentCarListBinding
import com.hiroozawa.ractsil.ui.MainActivityViewModel


class CarListFragment : Fragment() {

    private val viewModel by activityViewModels<MainActivityViewModel>()

    private lateinit var viewDataBinding: FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentCarListBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
            }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            viewDataBinding.carList.adapter = CarRecyclerViewAdapter(viewModel)
        } else {
            //log view model is not initialized
        }
    }

}
