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
    private var isDescending = true

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

            recyclerViewAdapter.toggleLinearVisibility(isSwitchOn)
        }

        binding.vipCinemaTv.setOnClickListener {
            sortByPrice { it.vipPrice }
        }

        binding.adultCinemaTv.setOnClickListener {
            sortByPrice { it.adultPrice }
        }

        binding.studentCinemaTv.setOnClickListener {
            sortByPrice { it.studentPrice }
        }

        binding.childCinemaTv.setOnClickListener {
            sortByPrice { it.childPrice }
        }

        binding.timeCinemaTv.setOnClickListener {
            val previousList = ArrayList(itemList)

            if (isDescending) {
                itemList.sortBy { it.timeMovieStart }
            } else {
                itemList.sortByDescending { it.timeMovieStart }
            }

            isDescending = !isDescending

            if (previousList != itemList) {
                recyclerViewAdapter.notifyItemRangeChanged(0, itemList.size)
            }
        }
    }

    private fun sortByPrice(priceType: (TicketItemModel) -> String) {
        val previousList = ArrayList(itemList)

        if (isDescending) {
            itemList.sortBy { priceType(it).extractPrice() }
        } else {
            itemList.sortByDescending { priceType(it).extractPrice() }
        }

        isDescending = !isDescending

        if (previousList != itemList) {
            recyclerViewAdapter.notifyItemRangeChanged(0, itemList.size)
        }
    }

    private fun String.extractPrice(): Int {
        return this.replace("€", "").trim().toInt()
    }

    private fun initRecyclerView() {
        itemList = arrayListOf(
            TicketItemModel(
                "18:30", "Dolby Atmos",
                "Cinema Europa", "18 €",
                "10 €", "13 €", "21 €", "05/06/2025", "Via Roma 10, Milano"
            ),
            TicketItemModel(
                "20:15", "IMAX",
                "Cineplex Firenze", "19 €",
                "9 €", "13 €", "23 €", "12/08/2025", "Piazza Duomo 5, Firenze"
            ),
            TicketItemModel(
                "14:00", "Standard",
                "Multisala Torino", "11 €",
                "5 €", "7 €", "14 €", "20/10/2025", "Corso Vittorio Emanuele 45, Torino"
            ),
            TicketItemModel(
                "19:45", "4DX",
                "Cinema Roma Center", "22 €",
                "11 €", "15 €", "26 €", "08/11/2025", "Via del Corso 90, Roma"
            ),
            TicketItemModel(
                "23:00", "VIP Lounge",
                "The Space Napoli", "28 €",
                "14 €", "18 €", "32 €", "31/12/2025", "Via Toledo 33, Napoli"
            ),
            TicketItemModel(
                "19:00", "4DX",
                "Cinema Europa", "17 €",
                "9 €", "13 €", "22 €", "12/04/2025", "Via Roma 10, Milano"
            ),
            TicketItemModel(
                "21:30", "Dolby Atmos",
                "Cineplex Firenze", "18 €",
                "9 €", "12 €", "22 €", "28/07/2025", "Piazza Duomo 5, Firenze"
            ),
            TicketItemModel(
                "16:15", "Standard",
                "Multisala Torino", "12 €",
                "6 €", "8 €", "15 €", "15/09/2025", "Corso Vittorio Emanuele 45, Torino"
            ),
            TicketItemModel(
                "20:00", "IMAX 3D",
                "Cinema Roma Center", "20 €",
                "10 €", "14 €", "25 €", "01/11/2025", "Via del Corso 90, Roma"
            ),
            TicketItemModel(
                "22:45", "VIP Lounge",
                "The Space Napoli", "24 €",
                "10 €", "14 €", "30 €", "22/12/2025", "Via Toledo 33, Napoli"
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