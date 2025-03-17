package com.absolute.cinema.ui.select

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentSelectSeatDialogBinding

class SelectSeatDialogFragment : DialogFragment() {

    private var _binding: FragmentSelectSeatDialogBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectSeatDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deselectSeatBtn.visibility = View.GONE
        setupListeners()

        val selectedTicketType = sharedViewModel.getSelectedTicketType()
        when (selectedTicketType) {
            "Adult" -> onSelectedTicket(binding.adultTicketTv)
            "Child" -> onSelectedTicket(binding.childTicketTv)
            "Student" -> onSelectedTicket(binding.studentTicketTv)
            "VIP" -> onSelectedTicket(binding.vipTicketTv)
        }
    }

    private fun setupListeners() {

        binding.closeTv.setOnClickListener {
            dismiss()
        }

        binding.deselectSeatBtn.setOnClickListener {
            resetTicketSelection()
        }

        binding.adultLinearLy.setOnClickListener {
            onSelectedTicket(binding.adultTicketTv)
            dismiss()
        }
        binding.childTicketTv.setOnClickListener {
            onSelectedTicket(binding.childTicketTv)
            dismiss()
        }
        binding.studentTicketTv.setOnClickListener {
            onSelectedTicket(binding.studentTicketTv)
            dismiss()
        }
        binding.vipTicketTv.setOnClickListener {
            onSelectedTicket(binding.vipTicketTv)
            dismiss()
        }
    }

    private fun onSelectedTicket(selectedTextView: TextView) {
        binding.adultLinearLy.visibility = View.GONE
        binding.childLinearLy.visibility = View.GONE
        binding.studentLinearLy.visibility = View.GONE
        binding.vipLinearLy.visibility = View.GONE

        when (selectedTextView.id) {
            binding.adultTicketTv.id -> binding.adultLinearLy.visibility = View.VISIBLE
            binding.childTicketTv.id -> binding.childLinearLy.visibility = View.VISIBLE
            binding.studentTicketTv.id -> binding.studentLinearLy.visibility = View.VISIBLE
            binding.vipTicketTv.id -> binding.vipLinearLy.visibility = View.VISIBLE
        }

        when (selectedTextView.id) {
            binding.adultTicketTv.id -> sharedViewModel.setSelectedTicketType("Adult")
            binding.childTicketTv.id -> sharedViewModel.setSelectedTicketType("Child")
            binding.studentTicketTv.id -> sharedViewModel.setSelectedTicketType("Student")
            binding.vipTicketTv.id -> sharedViewModel.setSelectedTicketType("VIP")
        }

        binding.deselectSeatBtn.visibility = View.VISIBLE
    }

    private fun resetTicketSelection() {
        sharedViewModel.setSelectedTicketType("")

        binding.adultLinearLy.visibility = View.VISIBLE
        binding.childLinearLy.visibility = View.VISIBLE
        binding.studentLinearLy.visibility = View.VISIBLE
        binding.vipLinearLy.visibility = View.VISIBLE
        binding.deselectSeatBtn.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setDimAmount(0.1F)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.decorView?.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
