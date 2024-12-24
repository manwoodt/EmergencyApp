package com.course.ex1.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.course.domain.model.Incident
import com.course.domain.model.UNDEFINED_ZONE_ID
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
    private lateinit var adapter: IncidentAdapter

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
        adapter = IncidentAdapter{incident ->
            showIncidentDetailsDialog(incident)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

    }



    private fun showIncidentDetailsDialog(incident: Incident){
        val message = "Status: ${incident.status}\n" +
                "Description: ${incident.description}\n" +
                "Coordinates: ${incident.latitude}, ${incident.longitude}"

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Incident № ${incident.incidentId} : ${incident.type}")
            .setMessage(message)
            .setPositiveButton("Close",null)
            .create()

        dialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}