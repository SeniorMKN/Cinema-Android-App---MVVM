package com.absolute.cinema.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absolute.cinema.data.domain.repository.MovieRepository
import com.absolute.cinema.data.remote.response.DetailsDto
import kotlinx.coroutines.launch

class AboutMovieViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    val moviesDetailsLiveData: LiveData<DetailsDto> get() = movieRepository.moviesDetailsLiveData

    fun fetchMovies(selectedMovieId: Int?) {
        viewModelScope.launch {
            movieRepository.fetchMoviesDetails(selectedMovieId)
        }
    }
}