package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.CardsItemModel
import com.absolute.cinema.databinding.RecyclerCardsLayoutBinding

class CardsRecyclerViewAdapter(private val itemList: ArrayList<CardsItemModel>) :
    RecyclerView.Adapter<CardsRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerCardsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CardsItemModel) {
            binding.cardIv.setImageResource(item.cardImage)
            binding.cardNumberTv.text = item.cardNumber
            binding.dateCardTv.text = item.cardDate

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardsRecyclerViewAdapter.MyViewHolder {
        val binding =
            RecyclerCardsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardsRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}