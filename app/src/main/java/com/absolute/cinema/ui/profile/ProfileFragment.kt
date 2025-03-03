package com.absolute.cinema.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.model.CardsItemModel
import com.absolute.cinema.data.model.HistoryItemModel
import com.absolute.cinema.databinding.FragmentProfileBinding
import com.absolute.cinema.ui.adapters.CardsRecyclerViewAdapter
import com.absolute.cinema.ui.adapters.HistoryRecyclerViewAdapter
import com.absolute.cinema.ui.card.CardDialogFragment
import com.absolute.cinema.ui.utils.ProfileSharedPreferences
import com.absolute.cinema.ui.utils.onBackPressed

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var cardItemList: ArrayList<CardsItemModel>
    private lateinit var moviePurchasedItemList: ArrayList<HistoryItemModel>
    private lateinit var cardsRecyclerViewAdapter: CardsRecyclerViewAdapter
    private lateinit var historyRecyclerViewAdapter: HistoryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()
        initRecyclerView()
        setupView()
    }

    private fun setupView() {
        binding.addNewCardBtn.setOnClickListener {
            CardDialogFragment().show(parentFragmentManager, "LoginDialog")
        }

        binding.backArrowTv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.logoutTv.setOnClickListener {
            ProfileSharedPreferences.clearData(requireContext())
            it.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        if (cardItemList.isEmpty()) {
            binding.cardsRecyclerView.visibility = View.GONE
        } else {
            binding.cardsRecyclerView.visibility = View.VISIBLE
        }

        if (moviePurchasedItemList.isEmpty()) {
            binding.historyRecyclerView.visibility = View.GONE
            binding.noTickets.visibility = View.VISIBLE
        } else {
            binding.historyRecyclerView.visibility = View.VISIBLE
            binding.noTickets.visibility = View.GONE
        }

    }

    private fun initRecyclerView() {

        cardItemList = arrayListOf(
            CardsItemModel(
                R.drawable.baseline_credit_card, "4716 •••• •••• 5615", "06/24"
            ),
        )

        moviePurchasedItemList = arrayListOf(
            HistoryItemModel(
                R.drawable.logo, "The Batman", "6 April 2022, 14:40", "Eurasia Cinema7"
            ),
        )

        cardsRecyclerViewAdapter = CardsRecyclerViewAdapter(cardItemList)
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cardsRecyclerView.adapter = cardsRecyclerViewAdapter

        historyRecyclerViewAdapter = HistoryRecyclerViewAdapter(moviePurchasedItemList)
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = historyRecyclerViewAdapter
    }

}