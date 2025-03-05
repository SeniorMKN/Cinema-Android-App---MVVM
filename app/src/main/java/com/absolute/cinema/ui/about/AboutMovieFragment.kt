package com.absolute.cinema.ui.about

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.absolute.cinema.data.remote.MovieDetails
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentAboutMovieBinding
import com.absolute.cinema.ui.tabs.TabsMovieFragment

class AboutMovieFragment : Fragment() {

    private var _binding: FragmentAboutMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AboutMovieViewModel by viewModels()
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initObserver()
        viewModel.fetchMovies(sharedViewModel.getSelectedMovieId())
        setupView()
        setupListeners()
    }

    private fun setupView() {

    }

    private fun initObserver() {
        viewModel.moviesDetailsLiveData.observe(viewLifecycleOwner) { moviesDetailsList ->
            Log.i("DETAILS_TAG", moviesDetailsList.toString())
        }
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
