package com.course.ex1.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.course.ex1.adapters.ZoneAdapter
import com.course.ex1.databinding.FragmentZonesBinding
import com.course.ex1.viewmodel.ZonesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZonesFragment : Fragment() {

    private var _binding: FragmentZonesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZonesViewModel by viewModels()
    private val adapter = ZoneAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentZonesBinding.inflate(inflater, container, false)

        setupRecyclerView()

        viewModel.zones.observe(viewLifecycleOwner){zones->
            adapter.setZones(zones)
        }

       return binding.root
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        adapter.onZoneClick = {zone ->
            val action = ZonesFragmentDirections.actionZonesFragmentToZoneDetailsFragment(zone.zoneId)
            findNavController().navigate(action)
        }
    }
}