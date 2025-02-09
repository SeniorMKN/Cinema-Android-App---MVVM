package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.databinding.RecyclerFilmLayoutBinding
import com.absolute.cinema.ui.data.RecyclerItemModel

class RecyclerViewAdapter(val itemList: ArrayList<RecyclerItemModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding: RecyclerFilmLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecyclerItemModel) {
            binding.imageFilm.setImageResource(item.img1)
            binding.titleFilm.text = item.title1
            binding.categoryFilm.text = item.category1

            binding.imageFilm1.setImageResource(item.img2)
            binding.titleFilm2.text = item.title2
            binding.categoryFilm2.text = item.category2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerFilmLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}