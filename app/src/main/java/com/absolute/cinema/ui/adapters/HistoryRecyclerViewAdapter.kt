package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.HistoryItemModel
import com.absolute.cinema.databinding.RecyclerHistoryLayoutBinding

class HistoryRecyclerViewAdapter (private val itemList: ArrayList<HistoryItemModel>) :
    RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerHistoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItemModel) {
            binding.movieImageIv.setImageResource(item.movieImage)
            binding.movieNameTv.text = item.movieName
            binding.movieDateTv.text = item.movieDate
            binding.cinemaNameTv.text = item.cinemaName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRecyclerViewAdapter.MyViewHolder {
        val binding =
            RecyclerHistoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}