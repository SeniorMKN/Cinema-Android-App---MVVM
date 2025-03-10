package com.absolute.cinema.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absolute.cinema.data.domain.repository.MovieRepository
import com.absolute.cinema.data.remote.response.MovieDetailsDto
import com.absolute.cinema.data.remote.response.Result
import kotlinx.coroutines.launch

class AboutMovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _moviesDetailsLiveData = MutableLiveData<MovieDetailsDto>()
    val moviesDetailsLiveData: LiveData<MovieDetailsDto> get() = _moviesDetailsLiveData

    private val _moviesVideoLiveData = MutableLiveData<List<Result>>()
    val moviesVideoLiveData: LiveData<List<Result>> get() = _moviesVideoLiveData

    fun loadMovieDetails(movieId: String) {
        viewModelScope.launch {
            val movies = movieRepository.loadMovieDetails(movieId)
            movies?.let {
                _moviesDetailsLiveData.postValue(it)
            }
        }
    }

    fun loadMovieVideo(movieId: String) {
        viewModelScope.launch {
            val videoMovies = movieRepository.videoMovies(movieId)
            videoMovies?.let {
                _moviesVideoLiveData.postValue(it)
            }
        }
    }
}