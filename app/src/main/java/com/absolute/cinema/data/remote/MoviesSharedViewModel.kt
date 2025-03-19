package com.absolute.cinema.data.remote

import androidx.lifecycle.ViewModel

class MoviesSharedViewModel : ViewModel() {
    private var selectedMovieId: String = ""
    private var selectedMovieTitle: String = ""
    private var selectedMovieDate: String = ""
    private var selectedMovieTime: String = ""
    private var selectedTicketType: String = ""
    private var selectedCinemaName: String = ""
    private var adultPrice: String = ""
    private var childPrice: String = ""
    private var studentPrice: String = ""
    private var vipPrice: String = ""
    private var selectedPoster: String = ""
    private val selectedMovieSeats: MutableList<String> = mutableListOf()
    private val selectedSeatsMap = mutableMapOf<String, String>()

    fun setSelectedMovieId(movieId: String) {
        selectedMovieId = movieId
    }

    fun getSelectedMovieId(): String {
        return selectedMovieId
    }

    fun setSelectedMovieTitle(movieTitle: String) {
        selectedMovieTitle = movieTitle
    }

    fun getSelectedMovieTitle(): String {
        return selectedMovieTitle
    }

    fun setSelectedDate(selectedDate: String) {
        selectedMovieDate = selectedDate
    }

    fun getSelectedDate(): String {
        return selectedMovieDate
    }

    fun setSelectedTime(selectedTime: String) {
        selectedMovieTime = selectedTime
    }

    fun getSelectedTime(): String {
        return selectedMovieTime
    }

    fun setSelectedCinema(selectedCinema: String) {
        selectedCinemaName = selectedCinema
    }

    fun getSelectedCinema(): String {
        return selectedCinemaName
    }

    fun getSelectedSeats(): List<String> {
        return selectedMovieSeats
    }

    fun setSelectedPosterPath(selectedPosterPath: String) {
        selectedPoster = selectedPosterPath
    }

    fun getSelectedPosterPath(): String {
        return selectedPoster
    }

    fun setSelectedTicketType(ticketType: String) {
        selectedTicketType = ticketType
    }

    fun setTicketPrices(adult: String, child: String, student: String, vip: String) {
        adultPrice = adult
        childPrice = child
        studentPrice = student
        vipPrice = vip
    }

    fun getAdultPrice(): String = adultPrice
    fun getChildPrice(): String = childPrice
    fun getStudentPrice(): String = studentPrice
    fun getVipPrice(): String = vipPrice

    fun setSelectedSeatType(seatNumber: String, ticketType: String?) {
        if (ticketType == null) {
            selectedSeatsMap.remove(seatNumber)
        } else {
            selectedSeatsMap[seatNumber] = ticketType
        }
    }

    fun getSelectedSeatType(): MutableMap<String, String> {
        return selectedSeatsMap
    }
}
