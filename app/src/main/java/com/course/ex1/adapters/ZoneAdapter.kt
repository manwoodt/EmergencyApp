package com.course.ex1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.course.domain.model.Zone
import com.course.domain.model.enums.Level
import com.course.ex1.R

class ZoneAdapter() :
    RecyclerView.Adapter<ZoneAdapter.ViewHolder>() {

    private val zoneList = mutableListOf<Zone>()
    var onZoneClick: ((Zone)-> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.zone_name)
        val colorIndicator: View = view.findViewById(R.id.zone_color)

        fun bind(zone: Zone, context: Context, onZoneClick: (Zone) -> Unit) {
            nameTextView.text = zone.name
            // Поменять на норм цвет!!!!!!!!!!!!
            colorIndicator.setBackgroundColor(getColorForLevel(zone.level, context))
            itemView.setOnClickListener { onZoneClick(zone) }
        }

        private  fun getColorForLevel(level: Level, context: Context): Int {
            return when (level) {
                Level.LOW -> ContextCompat.getColor(context, R.color.low_level)
                Level.MEDIUM -> ContextCompat.getColor(context, R.color.medium_level)
                Level.HIGH -> ContextCompat.getColor(context, R.color.high_level)
            }
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
        holder.bind(zone, holder.itemView.context) {
            // Обработка клика на элемент
            onZoneClick?.invoke(it)
        }
    }

    override fun getItemCount(): Int = zoneList.size


}