package com.example.appclone1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.appclone1.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard, container, false)
        binding.dateList.adapter = DateAdapter(requireContext())
        (binding.dateList.adapter as DateAdapter).submitList(listOf(20,21,22,23,24))
        binding.dateList.scrollToPosition((binding.dateList.adapter as DateAdapter).itemCount-1)

        return binding.root
    }

}