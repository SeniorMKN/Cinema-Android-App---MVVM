package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.CircleItemModel
import com.absolute.cinema.databinding.RecyclerCircleBinding

class CircleRecyclerViewAdapter(
    private val items: List<CircleItemModel>
) : RecyclerView.Adapter<CircleRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerCircleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CircleItemModel) {
            // Imposta l'immagine o altre informazioni
            binding.circle.setImageResource(item.circle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerCircleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}