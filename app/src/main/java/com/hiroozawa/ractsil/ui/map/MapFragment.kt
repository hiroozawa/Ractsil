package com.hiroozawa.ractsil.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.MainActivityViewModel

class MapFragment : Fragment(), OnMapReadyCallback,
    OnInfoWindowClickListener {

    private val viewModel by activityViewModels<MainActivityViewModel>()
    private lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
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

            if(carList.isNotEmpty()){
                val cameraUpdate = CameraUpdateFactory
                    .newLatLngBounds(boundsBuilder.build(), 0)
                map.moveCamera(cameraUpdate)
            }

        }

    override fun onInfoWindowClick(marker: Marker) {
        Toast.makeText(this.context, "Marker ${marker.title} was clicked", Toast.LENGTH_SHORT)
            .show()
    }
}
