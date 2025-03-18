package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.data.model.TicketItemModel
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.RecyclerTicketLayoutBinding

class TicketRecyclerViewAdapter(private val itemList: ArrayList<TicketItemModel>,private val sharedViewModel: MoviesSharedViewModel) :
    RecyclerView.Adapter<TicketRecyclerViewAdapter.MyViewHolder>() {

    private var isLinearVisible = false

    inner class MyViewHolder(private val binding: RecyclerTicketLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TicketItemModel) {
            binding.timeMovieStartTv.text = item.timeMovieStart
            binding.qualityCinemaTv.text = item.qualityCinema
            binding.cinemaNameTv.text = item.cinemaName
            binding.adultPriceTv.text = item.adultPrice
            binding.childPriceTv.text = item.childPrice
            binding.studentPriceTv.text = item.studentPrice
            binding.vipPriceTv.text = item.vipPrice
            binding.byCinemaNameTv.text = item.cinemaName
            binding.cinemaAddressTv.text = item.address
            binding.byCinemaNameTv.text = item.cinemaName

            binding.linearLy.visibility = if (isLinearVisible) View.VISIBLE else View.GONE

            setupView(binding, item)
        }
    }

    private fun setupView(binding: RecyclerTicketLayoutBinding, item: TicketItemModel) {
        binding.linearLyRv.setOnClickListener {
            sharedViewModel.setSelectedTime(item.timeMovieStart)
            sharedViewModel.setSelectedCinema(item.cinemaName)
            sharedViewModel.setTicketPrices(item.adultPrice,item.childPrice,item.studentPrice,item.vipPrice)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TicketRecyclerViewAdapter.MyViewHolder {
        val binding =
            RecyclerTicketLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun toggleLinearVisibility(isVisible: Boolean) {
        isLinearVisible = isVisible
        notifyItemRangeChanged(0, itemCount)
    }

}