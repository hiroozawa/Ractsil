package com.hiroozawa.ractsil.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.databinding.FragmentMapBinding
import com.hiroozawa.ractsil.ui.model.CarMapUiState
import com.hiroozawa.ractsil.ui.util.setupSnackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CarMapFragment() : DaggerFragment(), OnMapReadyCallback, OnMarkerClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CarMapViewModel> { viewModelFactory }
    private val args: CarMapFragmentArgs by navArgs()

    private lateinit var viewDataBinding: FragmentMapBinding
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMapBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
            }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupSnackBar()
        setupMap()

        viewModel.load(args.carId)
    }

    private fun setupMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun setupSnackBar() {
        view?.setupSnackbar(
            this.viewLifecycleOwner,
            viewModel.errorEvent,
            Snackbar.LENGTH_LONG
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMarkerClickListener(this)

        viewModel.carMapState.observe(this.viewLifecycleOwner, Observer { carMapState ->

            if (carMapState is CarMapUiState.MapUiData) {
                map.clear()
                val cameraUpdate = CameraUpdateFactory.newLatLngBounds(carMapState.latLngBounds, 0)
                map.moveCamera(cameraUpdate)

                carMapState.carMarkers.forEach { carMarkers ->
                    map.addMarker(carMarkers.markerOptions).tag = carMarkers.carId
                }
            }
        })
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val carId = marker.tag as String
        val navAction =
            CarMapFragmentDirections.actionNavigationMapToCarDetailFragment(carId)
        findNavController().navigate(navAction)
        return false
    }
}
