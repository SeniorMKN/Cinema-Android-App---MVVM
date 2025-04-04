package com.absolute.cinema.ui.pay

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.absolute.cinema.R
import com.absolute.cinema.data.local.movie.MovieDatabase
import com.absolute.cinema.data.local.movie.MovieTicket
import com.absolute.cinema.data.local.movie.MovieTicketDao
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentPayBinding
import com.absolute.cinema.ui.utils.UiUtils
import com.absolute.cinema.ui.utils.UiUtils.initGridLayout
import com.absolute.cinema.ui.utils.onBackPressed
import kotlinx.coroutines.launch

class PayFragment : Fragment() {

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()
    private lateinit var db: MovieDatabase
    private lateinit var ticketDao: MovieTicketDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()
        setupView()
        setupListeners()

        initGridLayout(binding.gridLy, requireContext())
    }

    private fun setupListeners() {
        binding.apply {

            db = MovieDatabase.getDatabase(requireContext())
            ticketDao = db.movieDao

            payContinueBtn.setOnClickListener {
                val ticket = MovieTicket(
                    movieId = sharedViewModel.getSelectedPosterPath(),
                    ticketDate = sharedViewModel.getSelectedDate(),
                    movieTitle = sharedViewModel.getSelectedMovieTitle(),
                    cinemaName = sharedViewModel.getSelectedCinema()
                )

                lifecycleScope.launch {
                    ticketDao.insert(ticket)
                }

                it.findNavController().navigate(R.id.action_payFragment_to_ticketFragment)
            }

            backArrowTv.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            val inputTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    val phoneNumber = phoneNumberEt.text.toString().trim()
                    val isValid = phoneNumber.length in 8..11
                    val orangeColor = requireContext().getColor(R.color.orange)

                    payContinueBtn.apply {
                        isEnabled = isValid
                        setBackgroundColor(if (isValid) orangeColor else UiUtils.brownColor)
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            }


            phoneNumberEt.addTextChangedListener(inputTextWatcher)
        }
    }

    private fun setupView() {
        binding.apply {
            ticketDateTv.text = getString(
                R.string.ticket_date_time,
                sharedViewModel.getSelectedDate(),
                sharedViewModel.getSelectedTime()
            )

            movieTitleTv.text = sharedViewModel.getSelectedMovieTitle()
            seatsNumberTv.text = sharedViewModel.getSelectedSeats().toString()
            cinemaNameTv.text = sharedViewModel.getSelectedCinema()
            seatsNumberTv.text = sharedViewModel.getSelectedSeatType().keys.joinToString(", ")

            payContinueBtn.apply {
                isEnabled = false
                setBackgroundColor(UiUtils.brownColor)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}