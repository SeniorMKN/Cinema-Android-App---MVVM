package com.absolute.cinema.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.databinding.FragmentHomeBinding
import com.absolute.cinema.ui.adapters.RecyclerViewAdapter
import com.absolute.cinema.data.model.MovieItemModel
import com.absolute.cinema.ui.city.CityDialogFragment
import com.absolute.cinema.ui.language.LanguageDialogFragment
import com.absolute.cinema.ui.login.LoginDialogFragment
import com.absolute.cinema.ui.utils.truncateToDecimalPlaces

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private val itemList = arrayListOf<MovieItemModel>()
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDialogs()
        setupRecyclerView()
        initObserver()

        viewModel.fetchMovies()
    }

    private fun initObserver() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { moviesList ->
            itemList.clear()

            for (i in moviesList.indices step 2) {
                val movieLeft = moviesList[i]
                val movieRight = if (i + 1 < moviesList.size) moviesList[i + 1] else null

                val movieItem = MovieItemModel(
                    movieImageLeft = "https://image.tmdb.org/t/p/w500" + movieLeft.posterPath,
                    movieRatingLeft = movieLeft.voteAverage.truncateToDecimalPlaces(1),
                    movieTitleLeft = movieLeft.title,
                    movieCategoryLeft = "Categoria Left",
                    movieImageRight = "https://image.tmdb.org/t/p/w500" + (movieRight?.posterPath ?: ""),
                    movieRatingRight = movieRight?.voteAverage?.truncateToDecimalPlaces(1) ?:"",
                    movieTitleRight = movieRight?.title ?: "",
                    movieCategoryRight = movieRight?.let { "Categoria Right" } ?: ""
                )

                itemList.add(movieItem)
            }

            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    private fun setupDialogs() {
        binding.loginButton.setOnClickListener {
            LoginDialogFragment().show(parentFragmentManager, "LoginDialog")
        }

        binding.positionName.setOnClickListener {
            CityDialogFragment().show(parentFragmentManager, "PositionDialog")
        }

        binding.languageName.setOnClickListener {
            LanguageDialogFragment().show(parentFragmentManager, "LanguageDialog")
        }
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(itemList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
