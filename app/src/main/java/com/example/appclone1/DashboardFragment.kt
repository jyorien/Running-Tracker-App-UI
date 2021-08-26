package com.example.appclone1

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.appclone1.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Dashboard"
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard, container, false)

        binding.dateList.adapter = DateAdapter(requireContext())
        (binding.dateList.adapter as DateAdapter).submitList(listOf(20, 21, 22, 23, 24))
        binding.dateList.scrollToPosition((binding.dateList.adapter as DateAdapter).itemCount - 1)

        binding.disTimeList.adapter = DistanceTimeAdapter()
        (binding.disTimeList.adapter as DistanceTimeAdapter).submitList(
            listOf(
                DisTime(1, "Distance", "Time"),
                DisTime(2, "4.23", "10.23"),
                DisTime(3, "4.13", "14.21"),
                DisTime(4, "3.23", "07.23"),
                DisTime(5, "4.23", "10.23"),
                DisTime(6, "4.23", "10.23"),
                DisTime(7, "4.23", "10.23"),
                DisTime(8, "4.23", "10.23"),
            )
        )
        initChart()
        setData()


        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        supportMapFragment.getMapAsync { map ->
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(1.39002037, 103.89750123), 18f))
            val markerOptions = MarkerOptions()
            markerOptions.apply {
                position(LatLng(1.38976832, 103.89741004))
            }
            val markerOptions2 = MarkerOptions()
            markerOptions2.position(LatLng(1.38966642, 103.89763534))
            map.addMarker(markerOptions)
            map.addMarker(markerOptions2)
            val polylineOptions = PolylineOptions()
            polylineOptions.color(ContextCompat.getColor(requireContext(), R.color.purple_200))
            polylineOptions.add(LatLng(1.38976832, 103.89741004), LatLng(1.38966642, 103.89763534))
            map.addPolyline(polylineOptions)
        }
        return binding.root
    }

    private fun initChart() {
        binding.lineChart.apply {
            setBackgroundColor(Color.WHITE)
            description.isEnabled = false
            setPinchZoom(true)
        }

        binding.lineChart.axisLeft.apply {
            axisMaximum = 200f
            axisMinimum = -50f
        }
        binding.lineChart.axisRight.isEnabled = false
    }

    private fun setData() {
        val values = arrayListOf<Entry>()
        for (i in 0..20) {
            val newEntry = Entry(i.toFloat(), (0..200).random().toFloat())
            values.add(newEntry)
        }

        val set1 = LineDataSet(values, "Dataset 1")
        set1.apply {
            setDrawIcons(false)
            color = Color.BLACK
            setCircleColor(Color.RED)
            set1.setDrawFilled(true)
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.green_gradient)
            fillDrawable = drawable
        }
        val datasets = mutableListOf<ILineDataSet>(set1)
        val lineData = LineData(datasets)
        binding.lineChart.data = lineData
    }

}