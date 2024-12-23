package com.course.ex1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.course.domain.model.Incident
import com.course.domain.model.Zone
import com.course.ex1.databinding.ItemIncidentBinding
import com.course.ex1.databinding.ItemZoneBinding

class IncidentAdapter() :
    RecyclerView.Adapter<IncidentAdapter.ViewHolder>() {

    private val incidentsList = mutableListOf<Incident>()

    class ViewHolder(val binding: ItemIncidentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(incident: Incident) {
            binding.incidentId.text = incident.incidentId.toString()
            binding.incidentStatus.text = incident.status
            binding.incidentType.text = incident.type
        }
    }

    fun setIncidents(incidents: List<Incident>) {
        incidentsList.clear()
        incidentsList.addAll(incidents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIncidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incident = incidentsList[position]
        holder.bind(incident)
    }

    override fun getItemCount(): Int = incidentsList.size


}