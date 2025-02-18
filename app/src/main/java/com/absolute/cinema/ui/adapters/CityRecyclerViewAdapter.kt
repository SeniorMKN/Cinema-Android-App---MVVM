package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.CityItemModel
import com.absolute.cinema.databinding.RecyclerCityLayoutBinding

class CityRecyclerViewAdapter(private val itemList: ArrayList<CityItemModel>) :
    RecyclerView.Adapter<CityRecyclerViewAdapter.MyViewHolder>() {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    inner class MyViewHolder(private val binding: RecyclerCityLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun defaultBg() {
            binding.checkIv.visibility = View.INVISIBLE
        }

        fun selectedBg() {
            binding.checkIv.visibility = View.VISIBLE
        }


        fun bind(item: CityItemModel) {
            binding.cityNameTv.text = item.cityName

        }

        init {
            itemView.setOnClickListener {
                selectedItemPos = adapterPosition
                if (lastItemSelectedPos == -1)
                    lastItemSelectedPos = selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    lastItemSelectedPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityRecyclerViewAdapter.MyViewHolder {
        val binding =
            RecyclerCityLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])
        if (position == selectedItemPos)
            holder.selectedBg()
        else
            holder.defaultBg()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}