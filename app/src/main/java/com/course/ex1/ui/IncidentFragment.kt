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


     //   val types = IncidentType.values().map { it.name }.toList()

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            IncidentType.values()
        ).also { adapter ->
            // добавить
            binding.incidentTypeSpinner.adapter = adapter
        }

        if (isCreating) {
            binding.etStatus.setText("NEW")
            binding.btnDeclare.visibility = View.VISIBLE
            binding.btnClose.visibility = View.GONE
        } else {
//            val currentType = incident?.type
//            val position = types.indexOf(currentType.name)

            val incidentType = incident?.type
            val position = IncidentType.values().indexOf(incidentType)
            binding.incidentTypeSpinner.setSelection(position)

            binding.etStatus.setText(incident?.status)
            binding.etDescription.setText(incident?.description)
            binding.etLatitude.setText(incident?.latitude.toString())
            binding.etLongitude.setText(incident?.longitude.toString())

            binding.incidentTypeSpinner.isEnabled = false
            binding.etStatus.isEnabled = false
            binding.etDescription.isEnabled = false
            binding.etLatitude.isEnabled = false
            binding.etLongitude.isEnabled = false

            binding.btnDeclare.visibility = View.GONE
            binding.btnClose.visibility = View.VISIBLE
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
                IncidentType.FIRE // значение по умолчанию
            }

            val newIncident = Incident(
                incidentId = null,
                type = incidentType,
                status = binding.etStatus.text.toString(),
                description = binding.etDescription.text.toString(),
                latitude = binding.etLatitude.text.toString().toDoubleOrNull() ?: 0.0,
                longitude = binding.etLongitude.text.toString().toDoubleOrNull() ?: 0.0
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


}