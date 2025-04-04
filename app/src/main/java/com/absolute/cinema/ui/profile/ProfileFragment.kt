package com.absolute.cinema.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.local.movie.MovieDatabase
import com.absolute.cinema.data.local.movie.MovieTicketDao
import com.absolute.cinema.data.model.CardsItemModel
import com.absolute.cinema.data.model.HistoryItemModel
import com.absolute.cinema.databinding.FragmentProfileBinding
import com.absolute.cinema.ui.adapters.CardsRecyclerViewAdapter
import com.absolute.cinema.ui.adapters.HistoryRecyclerViewAdapter
import com.absolute.cinema.ui.card.CardDialogFragment
import com.absolute.cinema.ui.utils.ProfileSharedPreferences
import com.absolute.cinema.ui.utils.onBackPressed
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var cardItemList: ArrayList<CardsItemModel>
    private lateinit var moviePurchasedItemList: ArrayList<HistoryItemModel>
    private lateinit var cardsRecyclerViewAdapter: CardsRecyclerViewAdapter
    private lateinit var historyRecyclerViewAdapter: HistoryRecyclerViewAdapter
    private lateinit var db: MovieDatabase
    private lateinit var ticketDao: MovieTicketDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = MovieDatabase.getDatabase(requireContext())
        ticketDao = db.movieDao

        onBackPressed()
        initRecyclerView()
        fetchTicketHistory()
        setupView()
    }

    private fun setupView() {
        binding.apply {
            addNewCardBtn.setOnClickListener {
                CardDialogFragment().show(parentFragmentManager, "LoginDialog")
            }

            backArrowTv.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            logoutTv.setOnClickListener {
                ProfileSharedPreferences.clearData(requireContext())
                it.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }

            paymentHistoryTv.setOnClickListener {
                lifecycleScope.launch {
                    ticketDao.deleteAllTickets()
                    moviePurchasedItemList.clear()
                    historyRecyclerViewAdapter.notifyDataSetChanged()

                    historyRecyclerView.visibility = View.GONE
                    noTickets.visibility = View.VISIBLE

                }
            }

            if (cardItemList.isEmpty()) {
                cardsRecyclerView.visibility = View.GONE
            } else {
                cardsRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            cardItemList = arrayListOf(
                CardsItemModel(
                    R.drawable.baseline_credit_card, "4716 •••• •••• 5615", "06/24"
                ),
            )

            moviePurchasedItemList = arrayListOf()

            cardsRecyclerViewAdapter = CardsRecyclerViewAdapter(cardItemList)
            cardsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            cardsRecyclerView.adapter = cardsRecyclerViewAdapter

            historyRecyclerViewAdapter = HistoryRecyclerViewAdapter(moviePurchasedItemList)
            historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            historyRecyclerView.adapter = historyRecyclerViewAdapter
        }
    }

    private fun fetchTicketHistory() {
        binding.apply {
            lifecycleScope.launch {
                val tickets = ticketDao.getAllTickets()

                moviePurchasedItemList.clear()
                tickets.forEach {
                    val historyItem = HistoryItemModel(
                        movieImage = it.movieId,
                        movieName = it.movieTitle,
                        movieDate = it.ticketDate,
                        cinemaName = it.cinemaName
                    )
                    moviePurchasedItemList.add(historyItem)
                }
                Log.i("TICKET", "$tickets ")
                historyRecyclerViewAdapter.notifyDataSetChanged()

                if (moviePurchasedItemList.isEmpty()) {
                    historyRecyclerView.visibility = View.GONE
                    noTickets.visibility = View.VISIBLE
                } else {
                    historyRecyclerView.visibility = View.VISIBLE
                    noTickets.visibility = View.GONE
                }
            }
        }
    }

}