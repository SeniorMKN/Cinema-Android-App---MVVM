package com.absolute.cinema.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentAboutMovieBinding
import com.absolute.cinema.ui.tabs.TabsMovieFragment
import com.absolute.cinema.ui.utils.VIDEO_MOVIE_PATH
import java.util.Locale

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

        setupIdMovies()
        setupListeners()
        setupObservers()
    }

    private fun setupIdMovies() {
        aboutMovieViewModel.loadMovieVideo(sharedViewModel.getSelectedMovieId())
        aboutMovieViewModel.loadMovieDetails(sharedViewModel.getSelectedMovieId())
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupObservers() {
        binding.apply {
            aboutMovieViewModel.moviesVideoLiveData.observe(viewLifecycleOwner) { result ->

                val video = VIDEO_MOVIE_PATH.replace("TEST", result.first().key)
                movieTrailerWv.loadData(video, "text/html", "utf-8")
                movieTrailerWv.webChromeClient = WebChromeClient()
                movieTrailerWv.settings.javaScriptEnabled = true

            }

            aboutMovieViewModel.moviesDetailsLiveData.observe(viewLifecycleOwner) { details ->

                movieDescriptionTv.text = details.overview
                releaseTv.text = details.releaseDate
                movieRatingAgeTv.text = if (details.isAdult) "18+" else "16+"
                genreTv.text = details.genres.joinToString { it.name }
                runtimeTv.text = details.runtime.let { runtime ->
                    String.format(Locale.getDefault(), "%02d:%02d", runtime / 60, runtime % 60)
                }
            }
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
