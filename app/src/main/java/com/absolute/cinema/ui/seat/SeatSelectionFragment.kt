package com.absolute.cinema.ui.seat

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.absolute.cinema.R
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentSeatSelectionBinding
import com.absolute.cinema.ui.select.SelectSeatDialogFragment
import com.absolute.cinema.ui.utils.onBackPressed
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SeatSelectionFragment : Fragment() {

    private var _binding: FragmentSeatSelectionBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()
    private val selectedSeatsMap = mutableMapOf<String, String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeatSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()
        setupView()
        setupListeners()
        setupFragmentResultListener()
    }

    private fun setupView() {
        val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            .format(Date())
            .split(" ")
            .joinToString(" ") { it.replaceFirstChar { ch -> ch.uppercaseChar() } }

        binding.calendarTimeTv.text = date

    }

    private fun setupFragmentResultListener(){
        parentFragmentManager.setFragmentResultListener(
            "seatSelection",
            this
        ) { _, bundle ->
            val seatNumber = bundle.getString("seatNumber") ?: return@setFragmentResultListener
            val ticketType = bundle.getString("ticketType")

            updateSeatSelection(seatNumber, ticketType)
        }
    }

    private fun setupListeners() {
        binding.constraintLayoutDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.buyTicketsBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_seatSelectionFragment_to_payFragment)
        }

        binding.backArrowTv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.constraintLayoutTime.setOnClickListener {
            openTimePicker()
        }

        toggleSeatSelection()
    }

    private fun toggleSeatSelection() {
        val seatButtons = mapOf(
            binding.seatNumberSix to "6",
            binding.seatNumberSeven to "7",
            binding.seatNumberEight to "8"
        )

        seatButtons.forEach { (button, seatNumber) ->
            button.setOnClickListener {
                val dialog = SelectSeatDialogFragment.newInstance(seatNumber, selectedSeatsMap[seatNumber])
                dialog.show(parentFragmentManager, "SelectSeatDialog")
            }
        }
    }

    private fun updateSeatSelection(seatNumber: String, ticketType: String?) {
        if (ticketType == null) {
            selectedSeatsMap.remove(seatNumber)
        } else {
            selectedSeatsMap[seatNumber] = ticketType
        }

        sharedViewModel.setSelectedSeatType(seatNumber, ticketType)
        binding.buyTicketsBtn.visibility = if (selectedSeatsMap.isNotEmpty()) View.VISIBLE else View.GONE
        updateSeatColors()
    }

    private fun updateSeatColors() {
        val seatButtons = mapOf(
            "6" to binding.seatNumberSix,
            "7" to binding.seatNumberSeven,
            "8" to binding.seatNumberEight
        )

        seatButtons.forEach { (seatNumber, button) ->
            if (selectedSeatsMap.containsKey(seatNumber) && selectedSeatsMap[seatNumber] != null) {
                button.setBackgroundColor(resources.getColor(R.color.orange, null))
            } else {
                button.setBackgroundColor(resources.getColor(R.color.main_app_bar_color, null))
            }
        }
    }

    private fun openTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                binding.dayTimeTv.text = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            },
            hour, minute, true
        )

        timePickerDialog.show()
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

                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                var formattedDate = dateFormat.format(selectedDate.time)

                formattedDate = formattedDate.replaceFirstChar { it.uppercase() }

                binding.calendarTimeTv.text = formattedDate
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