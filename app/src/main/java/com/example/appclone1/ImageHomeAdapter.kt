package com.example.appclone1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ImageHomeAdapter(val context: Context) : ListAdapter<String, ImageVH>(ImageItemComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        return ImageVH(view)
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        holder.bind(getItem(position), context)
    }
}

class ImageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.image_item)
    fun bind(res: String, context: Context) {
        val identifier = context.resources.getIdentifier(
            "drawable/$res",
            "drawable-v24",
            BuildConfig.APPLICATION_ID
        )
        image.setImageResource(identifier)
        image.setBackgroundResource(R.drawable.rounded_borders)
    }

}

class ImageItemComparator : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}