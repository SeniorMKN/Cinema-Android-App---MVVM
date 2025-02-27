package com.absolute.cinema.ui.sessions

import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        binding.calendarDateTv.setOnClickListener {
            showDatePickerDialog()
        }

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

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->

                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("MMMM, dd", Locale.getDefault())
                var formattedDate = dateFormat.format(selectedDate.time)

                formattedDate = formattedDate.replaceFirstChar { it.uppercase() }

                binding.calendarDateTv.text = formattedDate
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}