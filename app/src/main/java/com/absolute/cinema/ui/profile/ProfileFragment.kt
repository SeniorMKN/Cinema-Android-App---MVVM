package com.absolute.cinema.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.model.CardsItemModel
import com.absolute.cinema.data.model.HistoryItemModel
import com.absolute.cinema.databinding.FragmentProfileBinding
import com.absolute.cinema.ui.adapters.CardsRecyclerViewAdapter
import com.absolute.cinema.ui.adapters.HistoryRecyclerViewAdapter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemList: ArrayList<CardsItemModel>
    private lateinit var secondItemList: ArrayList<HistoryItemModel>
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
        initRecyclerView()
    }

    private fun initRecyclerView() {

        itemList = arrayListOf(
            CardsItemModel(
                R.drawable.baseline_credit_card, "4716 •••• •••• 5615", "06/24"
            ),
        )

        secondItemList = arrayListOf(
            HistoryItemModel(
                R.drawable.logo,"The Batman","6 April 2022, 14:40","Eurasia Cinema7"
            ),
        )

        cardsRecyclerViewAdapter = CardsRecyclerViewAdapter(itemList)
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cardsRecyclerView.adapter = cardsRecyclerViewAdapter

        historyRecyclerViewAdapter = HistoryRecyclerViewAdapter(secondItemList)
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter = historyRecyclerViewAdapter
    }

}