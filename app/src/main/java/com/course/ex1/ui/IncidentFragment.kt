package com.course.ex1.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.course.domain.model.Incident
import com.course.domain.model.enums.IncidentType
import com.course.domain.model.enums.Status
import com.course.ex1.adapters.IncidentAdapter
import com.course.ex1.databinding.DialogIncidentBinding
import com.course.ex1.databinding.FragmentIncidentBinding
import com.course.ex1.viewmodel.IncidentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentFragment : Fragment() {

    private var _bindingIncident: FragmentIncidentBinding? = null
    private val bindingIncident get() = _bindingIncident!!

    private val viewModel: IncidentViewModel by viewModels()
    private lateinit var adapter: IncidentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _bindingIncident = FragmentIncidentBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupViewModel()

        bindingIncident.btnDeclareIncident.setOnClickListener {
            showIncidentDialog(isCreating = true)
        }

        return bindingIncident.root
    }

    private fun setupRecyclerView() {
        adapter = IncidentAdapter { incident ->
            showIncidentDialog(incident,isCreating = false)
        }

        bindingIncident.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        bindingIncident.recyclerView.adapter = adapter

    }

    private fun setupViewModel(){
        viewModel.incidents.observe(viewLifecycleOwner) { incidents ->
            adapter.setIncidents(incidents)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                bindingIncident.progressBar.visibility = View.VISIBLE
            } else {
                bindingIncident.progressBar.visibility = View.GONE
            }
        }
    }


    private fun showIncidentDialog(incident: Incident? = null, isCreating: Boolean ) {
        val bindingDialog = DialogIncidentBinding.inflate(LayoutInflater.from(requireContext()))
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(bindingDialog.root)


        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            IncidentType.values()
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bindingDialog.incidentTypeSpinner.adapter = adapter
        }

        if (isCreating) {
            bindingDialog.dialogStatus.text = "NEW"
            bindingDialog.btnDeclare.visibility = View.VISIBLE
            bindingDialog.btnClose.visibility = View.GONE
        } else {
            showIncidentDetails(bindingDialog,incident)
        }

        val dialog = dialogBuilder.create()

        bindingDialog.btnDeclare.setOnClickListener {
            val newIncident = createIncident(bindingDialog)
            viewModel.addIncident(newIncident)
            dialog.dismiss()
        }

        bindingDialog.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        bindingDialog.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun createIncident(binding:DialogIncidentBinding):Incident{
        val selectedTypeString = binding.incidentTypeSpinner.selectedItem.toString()
        val incidentType = IncidentType.valueOf(selectedTypeString)

      return  Incident(
            incidentId = viewModel.incidents.value?.size?.toLong()?.plus(1),
            type = incidentType,
            status = Status.NEW,
            description = binding.dialogDescription.text.toString(),
            latitude = binding.dialogLatitude.text.toString().toDoubleOrNull() ?: 0.0,
            longitude = binding.dialogLongitude.text.toString().toDoubleOrNull() ?: 0.0,
            zoneId = 0
        )
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

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingIncident = null
    }
}