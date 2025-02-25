package com.absolute.cinema.ui.sessions

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.model.TicketItemModel
import com.absolute.cinema.databinding.FragmentSessionsMovieBinding
import com.absolute.cinema.ui.adapters.TicketRecyclerViewAdapter
import com.absolute.cinema.ui.sort.SortDialogFragment

class SessionsMovieFragment : Fragment() {

    private var _binding: FragmentSessionsMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: TicketRecyclerViewAdapter
    private lateinit var itemList: ArrayList<TicketItemModel>
    private var isSwitchOn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionsMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initRecyclerView()
    }

    private fun setupView() {

        binding.timeOrderTv.setOnClickListener {
            SortDialogFragment().show(parentFragmentManager, "SortDialog")
        }

        binding.cinemaOrderTv.setOnClickListener {
            isSwitchOn = !isSwitchOn

            val newDrawableRes = if (isSwitchOn) {
                R.drawable.resource_switch_on
            } else {
                R.drawable.resource_switch
            }

            val newDrawable = ContextCompat.getDrawable(requireContext(), newDrawableRes)

            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(
                binding.cinemaOrderTv, null, newDrawable, null, null
            )
        }
    }

    private fun initRecyclerView() {
        itemList = arrayListOf(
            TicketItemModel(
                "14:40", "Pyc",
                "Eurasia Cinema7", "2200",
                "1000 ₸", "1500 ₸", "3000 ₸"
            ),
            TicketItemModel(
                "14:40", "Pyc",
                "Eurasia Cinema7", "2200",
                "1000 ₸", "1500 ₸", "3000 ₸"
            )
        )

        recyclerViewAdapter = TicketRecyclerViewAdapter(itemList)
        binding.ticketRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.ticketRecyclerview.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}