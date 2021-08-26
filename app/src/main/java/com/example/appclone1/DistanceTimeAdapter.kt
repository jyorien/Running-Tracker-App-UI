package com.example.appclone1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DistanceTimeAdapter: ListAdapter<DisTime, DisTimeVH>(DisTimeComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisTimeVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.distance_time_layout, parent, false)
        return DisTimeVH(view)
    }

    override fun onBindViewHolder(holder: DisTimeVH, position: Int) {
        holder.bind(getItem(position))
    }
}
class DisTimeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val distanceView = itemView.findViewById<TextView>(R.id.distance)
    val timeView = itemView.findViewById<TextView>(R.id.time)

    fun bind(disTime: DisTime) {
        distanceView.text = disTime.distance
        timeView.text = disTime.time
    }

}
class DisTimeComparator: DiffUtil.ItemCallback<DisTime>() {
    override fun areItemsTheSame(oldItem: DisTime, newItem: DisTime): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DisTime, newItem: DisTime): Boolean {
        return oldItem.id == newItem.id
    }

}