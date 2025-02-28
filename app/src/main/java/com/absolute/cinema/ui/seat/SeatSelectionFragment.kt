package com.absolute.cinema.ui.seat

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentSeatSelectionBinding
import com.absolute.cinema.ui.utils.onBackPressed
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SeatSelectionFragment : Fragment() {

    private var _binding: FragmentSeatSelectionBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun setupView() {
        binding.calendarTimeTv.setOnClickListener {
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

                val dateFormat = SimpleDateFormat("MMMM, dd", Locale.getDefault())
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