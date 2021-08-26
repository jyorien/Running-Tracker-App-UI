package com.example.appclone1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.appclone1.databinding.FragmentRunBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class RunFragment : Fragment() {
    private lateinit var binding: FragmentRunBinding
    private var googleMap: GoogleMap? = null
    private lateinit var client: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private val locationCallback =  object: LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            val coords = LatLng(result.lastLocation.latitude, result.lastLocation.longitude)
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,14f))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Record"
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        client = LocationServices.getFusedLocationProviderClient(requireContext())
        locationRequest = LocationRequest.create()
        locationRequest.interval = 4000
        locationRequest.fastestInterval = 2000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_run, container, false)
        (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
        requestPermission()
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        supportMapFragment.getMapAsync {
            googleMap = it
            (googleMap as GoogleMap).apply {

            }
        }
        return binding.root
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap?.isMyLocationEnabled = true
            client.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())
            return
        }

        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            Snackbar.make(binding.root, "Permission granted", Snackbar.LENGTH_SHORT).show()
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            googleMap?.isMyLocationEnabled = true
            client.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper())
        }
    }


}