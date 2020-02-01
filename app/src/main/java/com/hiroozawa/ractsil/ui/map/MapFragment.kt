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
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.ui.MainActivityViewModel

class MapFragment : Fragment(),OnMapReadyCallback,
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
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Car Name")
                .snippet("Touch to show on list")

        )
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onInfoWindowClick(marker: Marker) {
        Toast.makeText(this.context,"Marker ${marker.title} was clicked",Toast.LENGTH_SHORT).show()
    }
}
