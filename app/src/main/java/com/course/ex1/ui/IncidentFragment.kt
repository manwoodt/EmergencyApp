package com.course.ex1.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.core.content.ContentProviderCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.course.domain.model.Incident
import com.course.domain.model.UNDEFINED_ZONE_ID
import com.course.domain.model.enums.IncidentType
import com.course.domain.model.enums.Status
import com.course.ex1.R
import com.course.ex1.adapters.IncidentAdapter
import com.course.ex1.adapters.ZoneAdapter
import com.course.ex1.databinding.DialogIncidentBinding
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

        binding.btnDeclareIncident.setOnClickListener {
            showIncidentDialog(isCreating = true)
        }

        viewModel.incidents.observe(viewLifecycleOwner) { incidents ->
            adapter.setIncidents(incidents)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = IncidentAdapter { incident ->
            showIncidentDialog(incident,isCreating = false)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun showIncidentDialog(incident: Incident? = null, isCreating: Boolean ) {
        val binding = DialogIncidentBinding.inflate(LayoutInflater.from(requireContext()))
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(binding.root)


        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            IncidentType.values()
        ).also { adapter ->
            // добави
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.incidentTypeSpinner.adapter = adapter
        }

        if (isCreating) {
            binding.dialogStatus.text = "NEW"
            binding.btnDeclare.visibility = View.VISIBLE
            binding.btnClose.visibility = View.GONE
        } else {
            showIncidentDetails(binding,incident)
        }

        val dialog = dialogBuilder.create()

        // Обработчики кнопок
        binding.btnDeclare.setOnClickListener {

//            val selectedTypeString = binding.incidentTypeSpinner.selectedItem.toString()
//            val incidentType = IncidentType.valueOf(selectedTypeString)

            val selectedTypeString = binding.incidentTypeSpinner.selectedItem.toString()
            val incidentType = try {
                IncidentType.valueOf(selectedTypeString)
            } catch (e: IllegalArgumentException) {
                IncidentType.OTHER // значение по умолчанию
            }

            val newIncident = Incident(
                incidentId = null,
                type = incidentType,
                status = Status.NEW.toString(),
                description = binding.dialogDescription.text.toString(),
                latitude = binding.dialogLatitude.text.toString().toDoubleOrNull() ?: 0.0,
                longitude = binding.dialogLongitude.text.toString().toDoubleOrNull() ?: 0.0
            )
            viewModel.addIncident(newIncident)
            dialog.dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showIncidentDetails(binding:DialogIncidentBinding, incident: Incident?){
        if (incident != null){
            val incidentType = incident.type
            val position = IncidentType.values().indexOf(incidentType)
            binding.incidentTypeSpinner.setSelection(position)

            binding.dialogStatus.text = "Incident status : \"${incident.status}\""
            binding.dialogDescription.setText(incident.description)
            binding.dialogLatitude.setText(incident.latitude.toString())
            binding.dialogLongitude.setText(incident.longitude.toString())

            binding.incidentTypeSpinner.isEnabled = false
            binding.dialogStatus.isEnabled = false
            binding.dialogDescription.isEnabled = false
            binding.dialogLatitude.isEnabled = false
            binding.dialogLongitude.isEnabled = false

            binding.btnDeclare.visibility = View.GONE
            binding.btnCancel.visibility = View.GONE
            binding.btnClose.visibility = View.VISIBLE
        }
    }


}