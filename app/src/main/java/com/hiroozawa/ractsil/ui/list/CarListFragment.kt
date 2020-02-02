package com.hiroozawa.ractsil.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hiroozawa.ractsil.databinding.FragmentCarListBinding
import com.hiroozawa.ractsil.ui.EventObserver
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
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigation()
    }

    private fun setupListAdapter() {
        viewDataBinding.viewmodel?.let {
            viewDataBinding.carList.adapter = CarRecyclerViewAdapter(it)
        }
    }

    private fun setupNavigation() {
        viewModel.openCarMapEvent.observe(this.viewLifecycleOwner, EventObserver { event ->
            val actionNavigationListToNavigationMap =
                CarListFragmentDirections.actionNavigationListToNavigationMap().apply {
                    carId = event
                }
            findNavController().navigate(actionNavigationListToNavigationMap)
        })
    }

}
