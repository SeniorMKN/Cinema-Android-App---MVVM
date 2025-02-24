package com.absolute.cinema.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.R
import com.absolute.cinema.databinding.RecyclerMovieLayoutBinding
import com.absolute.cinema.data.model.MovieItemModel

class RecyclerViewAdapter(private val itemList: ArrayList<MovieItemModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerMovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemModel) {
            binding.imageMovieLeft.setImageResource(item.movieImageLeft)
            binding.ratingMovieLeft.text = item.movieRatingLeft
            binding.titleMovieLeft.text = item.movieTitleLeft
            binding.categoryMovieLeft.text = item.movieCategoryLeft

            binding.imageMovieRight.setImageResource(item.movieImageRight)
            binding.ratingMovieRight.text = item.movieRatingRight
            binding.titleMovieRight.text = item.movieTitleRight
            binding.categoryMovieRight.text = item.movieCategoryRight

            setupView(binding)
        }
    }

    private fun setupView(binding: RecyclerMovieLayoutBinding){
        binding.imageMovieLeft.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_tabsMovieFragment)
        }
        binding.imageMovieRight.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_tabsMovieFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerMovieLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}