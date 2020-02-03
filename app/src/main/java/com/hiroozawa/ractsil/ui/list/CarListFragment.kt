package com.hiroozawa.ractsil.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hiroozawa.ractsil.databinding.FragmentCarListBinding
import com.hiroozawa.ractsil.ui.EventObserver
import com.hiroozawa.ractsil.ui.util.setupSnackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CarListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CarListViewModel> { viewModelFactory }

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
        setupSnackBar()
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

    private fun setupSnackBar() {
        view?.setupSnackbar(
            this.viewLifecycleOwner,
            viewModel.errorEvent,
            Snackbar.LENGTH_LONG
        )
    }
}
