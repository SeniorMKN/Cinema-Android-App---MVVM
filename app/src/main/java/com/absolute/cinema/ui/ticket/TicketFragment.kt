package com.absolute.cinema.ui.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.model.CircleItemModel
import com.absolute.cinema.databinding.FragmentTicketBinding
import com.absolute.cinema.ui.adapters.CircleRecyclerViewAdapter

class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = List(14) { CircleItemModel(R.drawable.circle) }

        binding.circleRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.circleRecyclerview.adapter = CircleRecyclerViewAdapter(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}