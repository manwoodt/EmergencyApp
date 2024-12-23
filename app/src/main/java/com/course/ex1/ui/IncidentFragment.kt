package com.course.ex1.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.course.ex1.adapters.IncidentAdapter
import com.course.ex1.adapters.ZoneAdapter
import com.course.ex1.databinding.FragmentIncidentBinding
import com.course.ex1.databinding.FragmentZonesBinding
import com.course.ex1.viewmodel.IncidentViewModel
import com.course.ex1.viewmodel.ZonesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentFragment : Fragment() {

    private var _binding: FragmentIncidentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: IncidentViewModel by viewModels()
    private val adapter = IncidentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentIncidentBinding.inflate(inflater, container, false)

        setupRecyclerView()

        viewModel.incidents.observe(viewLifecycleOwner){incidents->
           adapter.setIncidents(incidents)
        }

        return binding.root
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

    }
}