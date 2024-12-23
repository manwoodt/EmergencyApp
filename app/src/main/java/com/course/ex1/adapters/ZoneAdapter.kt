package com.course.ex1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.course.domain.model.Zone
import com.course.ex1.databinding.ItemZoneBinding

class ZoneAdapter() :
    RecyclerView.Adapter<ZoneAdapter.ViewHolder>() {

    private val zoneList = mutableListOf<Zone>()
    var onZoneClick: ((Zone) -> Unit)? = null

    class ViewHolder(val binding: ItemZoneBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(zone: Zone, onZoneClick: (Zone) -> Unit) {
            binding.zoneName.text = zone.name
            binding.zoneColor.setBackgroundColor(zone.level)
            itemView.setOnClickListener { onZoneClick(zone) }

        }
    }

    fun setZones(zones: List<Zone>) {
        zoneList.clear()
        zoneList.addAll(zones)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemZoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zone = zoneList[position]
        holder.bind(zone) {
            onZoneClick?.invoke(it)
        }
    }

    override fun getItemCount(): Int = zoneList.size


}