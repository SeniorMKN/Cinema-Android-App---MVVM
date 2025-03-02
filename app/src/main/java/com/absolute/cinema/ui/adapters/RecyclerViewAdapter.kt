package com.absolute.cinema.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.R
import com.absolute.cinema.data.remote.respond.MovieDto
import com.absolute.cinema.data.remote.response.MovieDto
import com.absolute.cinema.databinding.RecyclerMovieLayoutBinding
import com.absolute.cinema.ui.utils.BASE_BACKGROUND_IMAGE_PATH
import com.absolute.cinema.ui.utils.truncateToDecimalPlaces
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val movieList: List<MovieDto>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: RecyclerMovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieLeft: MovieDto, movieRight: MovieDto?) {

            // Left Column Movies
            binding.apply {
                Glide.with(root.context)
                    .load(BASE_BACKGROUND_IMAGE_PATH + movieLeft.posterPath)
                    .centerCrop()
                    .into(imageMovieLeft)

                ratingMovieLeft.text = movieLeft.voteAverage.truncateToDecimalPlaces(1)
                titleMovieLeft.text = movieLeft.title
                releaseDateLeft.text = movieLeft.releaseDate
            }

            // Right Column Movies
            if (movieRight != null) {
                binding.apply {
                    Glide.with(root.context)
                        .load(BASE_BACKGROUND_IMAGE_PATH + movieRight.posterPath)
                        .centerCrop()
                        .into(imageMovieRight)

                    ratingMovieRight.text = movieRight.voteAverage.truncateToDecimalPlaces(1)
                    titleMovieRight.text = movieRight.title
                    releaseDateRight.text = movieRight.releaseDate
                }
            }

            setupView(binding, movieLeft, movieRight)
        }
    }

    private fun setupView(binding: RecyclerMovieLayoutBinding, movieLeft: MovieDto, movieRight: MovieDto?) {
        binding.imageMovieLeft.setOnClickListener {
            val bundle = Bundle().apply {
                putString("movieTitle", movieLeft.title)
            }
            binding.root.findNavController().navigate(R.id.action_homeFragment_to_tabsMovieFragment, bundle)
        }

        binding.imageMovieRight.setOnClickListener {
            movieRight?.let {
                val bundle = Bundle().apply {
                    putString("movieTitle", it.title)
                }
                binding.root.findNavController().navigate(R.id.action_homeFragment_to_tabsMovieFragment, bundle)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerMovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieLeft = movieList[position * 2]
        val movieRight =
            if (position * 2 + 1 < movieList.size) movieList[position * 2 + 1] else null

        holder.bind(movieLeft, movieRight)
    }

    override fun getItemCount(): Int {
        // Divide the list size by 2 to group items in pairs
        return (movieList.size + 1) / 2
    }
}