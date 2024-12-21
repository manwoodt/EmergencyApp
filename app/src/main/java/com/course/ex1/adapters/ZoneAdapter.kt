package com.course.ex1.adapters

import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ZoneAdapter:
    RecyclerView.Adapter<ZoneAdapter.ViewHolder>() {

        class ViewHolder(view:View): RecyclerView.ViewHolder(view){
            val nameTextView: TextView = view.findViewById(R.id.zone_name)
            val colorIndicator: View = view.findViewById(R.id.zone_color)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ZoneAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}