package com.absolute.cinema.ui.sessions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.data.model.TicketItemModel
import com.absolute.cinema.databinding.FragmentSessionsMovieBinding
import com.absolute.cinema.ui.adapters.TicketRecyclerViewAdapter

class SessionsMovieFragment : Fragment() {

    private var _binding: FragmentSessionsMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: TicketRecyclerViewAdapter
    private lateinit var itemList: ArrayList<TicketItemModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        itemList = arrayListOf(
            TicketItemModel(
                "14:40", "Pyc",
                "Eurasia Cinema7", "2200",
                "1000 ₸", "1500 ₸","3000 ₸"
            ),
            TicketItemModel(
                "14:40", "Pyc",
                "Eurasia Cinema7", "2200",
                "1000 ₸", "1500 ₸","3000 ₸"
            )
        )

        recyclerViewAdapter = TicketRecyclerViewAdapter(itemList)
        binding.ticketRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.ticketRecyclerview.adapter = recyclerViewAdapter
    }
}