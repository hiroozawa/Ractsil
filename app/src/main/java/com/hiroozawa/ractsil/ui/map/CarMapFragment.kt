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
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.util.setupSnackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CarMapFragment : DaggerFragment(), OnMapReadyCallback,
    OnInfoWindowClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CarMapViewModel> { viewModelFactory }
    private val args: CarMapFragmentArgs by navArgs()

    private lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSnackBar()
        setupMap()
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

        map.setOnInfoWindowClickListener(this)

        viewModel.cars.observe(this, Observer(addListToMakers()))
    }
    private fun addListToMakers(): (List<Car>) -> Unit =
        { carList ->

            map.clear()
            val boundsBuilder = LatLngBounds.Builder()

            carList.map { car ->
                val latLng = LatLng(car.coordinate.latitude, car.coordinate.longitude)
                boundsBuilder.include(latLng)

                MarkerOptions()
                    .position(latLng)
                    .title(car.owner.name)
                    .snippet("Model: ${car.model.modelName}\n")


            }.forEach {
                map.addMarker(it)
            }


            if (args.carId.isNullOrEmpty()) {
                if (carList.isNotEmpty()) {
                    val cameraUpdate = CameraUpdateFactory
                        .newLatLngBounds(boundsBuilder.build(), 0)
                    map.moveCamera(cameraUpdate)
                }
            } else {
                if (carList.isNotEmpty()) {

                    val car = carList.firstOrNull() { it.carId.id == args.carId }

                    car?.let {

                        val latl = LatLng(
                            car.coordinate.latitude,
                            car.coordinate.longitude
                        )
                        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                            latl,
                            15F
                        )
                        map.moveCamera(cameraUpdate)
                    }

                }
            }

        }

    override fun onInfoWindowClick(marker: Marker) {
    }
}
