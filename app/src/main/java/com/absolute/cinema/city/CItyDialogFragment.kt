package com.absolute.cinema.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.model.CardsItemModel
import com.absolute.cinema.data.model.CityItemModel
import com.absolute.cinema.data.model.HistoryItemModel
import com.absolute.cinema.databinding.FragmentCityDialogBinding
import com.absolute.cinema.databinding.FragmentLoginDialogBinding
import com.absolute.cinema.ui.adapters.CardsRecyclerViewAdapter
import com.absolute.cinema.ui.adapters.CityRecyclerViewAdapter
import com.absolute.cinema.ui.adapters.HistoryRecyclerViewAdapter


class CItyDialogFragment : Fragment() {

    private var _binding: FragmentCityDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemList: ArrayList<CityItemModel>
    private lateinit var RecyclerViewAdapter: CityRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {

        itemList = arrayListOf(
            CityItemModel(
                "Almaty",
            ),
        )

        RecyclerViewAdapter = CityRecyclerViewAdapter(itemList)
        binding.cityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cityRecyclerView.adapter = RecyclerViewAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}