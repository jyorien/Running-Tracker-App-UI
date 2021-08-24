package com.example.appclone1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.appclone1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        (activity as MainActivity).supportActionBar!!.title = "Home"
        binding.imageList.adapter = ImageHomeAdapter(requireContext())
        (binding.imageList.adapter as ImageHomeAdapter).submitList(listOf("run","run","run"))
        return binding.root
    }


}