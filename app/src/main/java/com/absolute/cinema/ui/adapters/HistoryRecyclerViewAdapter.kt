package com.absolute.cinema.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.HistoryItemModel
import com.absolute.cinema.databinding.RecyclerHistoryLayoutBinding
import com.absolute.cinema.ui.utils.BASE_BACKGROUND_IMAGE_PATH
import com.bumptech.glide.Glide

class HistoryRecyclerViewAdapter(private val itemList: ArrayList<HistoryItemModel>) :
    RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerHistoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItemModel) {
            binding.apply {
                Glide.with(root.context)
                    .load(BASE_BACKGROUND_IMAGE_PATH + item.movieImage)
                    .into(binding.movieImageIv)
                movieNameTv.text = item.movieName
                movieDateTv.text = item.movieDate
                cinemaNameTv.text = item.cinemaName
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryRecyclerViewAdapter.MyViewHolder {
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