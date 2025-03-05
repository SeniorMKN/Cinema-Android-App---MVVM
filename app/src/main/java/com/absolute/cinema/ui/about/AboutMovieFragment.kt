package com.absolute.cinema.ui.about

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentAboutMovieBinding
import com.absolute.cinema.ui.tabs.TabsMovieFragment

class AboutMovieFragment : Fragment() {

    private var _binding: FragmentAboutMovieBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()
    private val aboutMovieViewModel: AboutMovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupView()
        setupObservers()

        aboutMovieViewModel.loadMovieDetails(sharedViewModel.getSelectedMovieId())
    }

    private fun setupObservers() {
        aboutMovieViewModel.moviesDetailsLiveData.observe(viewLifecycleOwner) { details ->
            binding.apply {
                movieRatingAgeTv.text = details.releaseDate
            }
        }
    }

    private fun setupView() {
        binding.movieRatingAgeTv.text = sharedViewModel.getSelectedMovieId()
    }

    private fun setupListeners() {
        binding.selectSessionBtn.setOnClickListener {
            (requireParentFragment() as? TabsMovieFragment)?.navigateToSeatSelection()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
