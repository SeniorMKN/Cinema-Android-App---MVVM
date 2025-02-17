package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.CityItemModel
import com.absolute.cinema.databinding.RecyclerCityLayoutBinding

class CityRecyclerViewAdapter (private val itemList: ArrayList<CityItemModel>) :
    RecyclerView.Adapter<CityRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerCityLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CityItemModel) {
            binding.cityNameTv.text = item.cityName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityRecyclerViewAdapter.MyViewHolder {
        val binding =
            RecyclerCityLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}