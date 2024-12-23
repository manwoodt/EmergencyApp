package com.course.ex1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.course.domain.model.Zone
import com.course.ex1.R

class ZoneAdapter :
    RecyclerView.Adapter<ZoneAdapter.ViewHolder>() {

    private val zoneList = mutableListOf<Zone>()
    var onZoneClick: ((Zone)-> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.zone_name)
        val colorIndicator: View = view.findViewById(R.id.zone_color)

        fun bind(zone: Zone, onZoneClick: (Zone) -> Unit) {
            nameTextView.text = zone.name
            // Поменять на норм цвет!!!!!!!!!!!!
            colorIndicator.setBackgroundColor(34223)
            itemView.setOnClickListener { onZoneClick(zone) }
        }
    }

    // Функция для обновления данных
    fun setZones(zones: List<Zone>) {
        zoneList.clear()
        zoneList.addAll(zones)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_zone, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZoneAdapter.ViewHolder, position: Int) {
        val zone = zoneList[position]
        holder.bind(zone) {
            // Обработка клика на элемент
            onZoneClick?.invoke(it)
        }
    }

    override fun getItemCount(): Int = zoneList.size


}