package com.course.ex1.ui

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.course.ex1.R
import com.course.ex1.databinding.FragmentZoneDetailsBinding
import com.course.ex1.databinding.FragmentZonesBinding
import com.course.ex1.viewmodel.ZoneDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZoneDetailsFragment : Fragment() {

    private var _binding : FragmentZoneDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZoneDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentZoneDetailsBinding.inflate(inflater,container,false)

        val zoneId = arguments?.getLong("zoneId")?: throw IllegalArgumentException("Zone ID missing")

        viewModel.loadZoneDetails(zoneId)

        viewModel.zone.observe(viewLifecycleOwner){zone->
            val coordinates =  "${zone.minLatitude}° : ${zone.maxLatitude}°, ${zone.minLongitude}° : ${zone.maxLongitude}°"

            binding.zoneName.text = zone.name
            binding.zonePhone.text = zone.phone
            binding.coordinatesValue.text = coordinates
            binding.zoneCard.setBackgroundColor(zone.level)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}