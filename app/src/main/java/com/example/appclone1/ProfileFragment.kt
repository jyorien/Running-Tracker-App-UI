package com.example.appclone1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.appclone1.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Profile"
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_profile, container, false)

        binding.genderSpinner.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.genders, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        }
        binding.genderSpinner.setSelection(1)
        val stylePref = requireActivity().getSharedPreferences("style", Context.MODE_PRIVATE)
        if (stylePref.getString("style","GREEN") == "PURPLE")
            binding.themeSwitch.isChecked = true
        binding.themeSwitch.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked)
                stylePref.edit().putString("style","PURPLE").apply()
            else
                stylePref.edit().putString("style","GREEN").apply()
            (activity as MainActivity).recreate()
        }
        return binding.root
    }


}