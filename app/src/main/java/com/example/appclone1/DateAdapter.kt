package com.example.appclone1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DateAdapter(val context: Context): ListAdapter<Int, DateVH>(DateComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_list_item, parent, false)
        return DateVH(view)
    }

    override fun onBindViewHolder(holder: DateVH, position: Int) {
        holder.bind(getItem(position))
        if (position == itemCount-1) {
            holder.setBackgroundGreen(context)
        }
    }
}
class DateVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val day = itemView.findViewById<TextView>(R.id.day)
    fun bind(date: Int) {
        day.text = date.toString()
        day.elevation = 5f
    }
    fun setBackgroundGreen(context: Context) {
        day.setBackgroundResource(R.drawable.green_rounded_border)
        val scale = context.resources.displayMetrics.density
        day.setPadding(((17*scale+0.5f).toInt()))
    }

}
class DateComparator: DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

}