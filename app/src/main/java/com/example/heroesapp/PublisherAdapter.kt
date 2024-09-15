package com.example.heroesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PublisherAdapter(
    private val publisherList: List<Publisher>,
    private val onItemClick: (Publisher) -> Unit
) : RecyclerView.Adapter<PublisherAdapter.PublisherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_publisher, parent, false)
        return PublisherViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        val publisher = publisherList[position]
        holder.bind(publisher, onItemClick)
    }

    override fun getItemCount(): Int = publisherList.size

    class PublisherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.publisherName)
        private val imageView: ImageView = itemView.findViewById(R.id.publisherImage)

        fun bind(publisher: Publisher, onItemClick: (Publisher) -> Unit) {
            nameTextView.text = publisher.name
            Picasso.get().load(publisher.image).into(imageView)
            itemView.setOnClickListener { onItemClick(publisher) }
        }
    }
}
