package com.absolute.cinema.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.data.model.CityItemModel
import com.absolute.cinema.databinding.FragmentCityDialogBinding
import com.absolute.cinema.ui.adapters.CityRecyclerViewAdapter

class CItyDialogFragment : DialogFragment() {

    private var _binding: FragmentCityDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemList: ArrayList<CityItemModel>
    private lateinit var recyclerViewAdapter: CityRecyclerViewAdapter

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

        recyclerViewAdapter = CityRecyclerViewAdapter(itemList)
        binding.cityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cityRecyclerView.adapter = recyclerViewAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}