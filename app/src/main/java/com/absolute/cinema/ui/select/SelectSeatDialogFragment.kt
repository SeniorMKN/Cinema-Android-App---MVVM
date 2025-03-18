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
import com.absolute.cinema.ui.seat.SeatSelectionFragment
import com.absolute.cinema.ui.utils.ADULT
import com.absolute.cinema.ui.utils.CHILD
import com.absolute.cinema.ui.utils.STUDENT
import com.absolute.cinema.ui.utils.VIP

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

        val seatNumber = arguments?.getString("seatNumber") ?: return
        val selectedTicketType = arguments?.getString("ticketType")
        setupListeners(seatNumber)

        when (selectedTicketType) {
            ADULT -> onSelectedTicket(binding.adultTicketTv)
            CHILD -> onSelectedTicket(binding.childTicketTv)
            STUDENT -> onSelectedTicket(binding.studentTicketTv)
            VIP -> onSelectedTicket(binding.vipTicketTv)
        }
    }

    private fun setupListeners(seatNumber: String) {
        binding.closeTv.setOnClickListener {
            dismiss()
        }

        binding.deselectSeatBtn.setOnClickListener {
            resetTicketSelection()
        }

        binding.adultLinearLy.setOnClickListener {
            onSelectedTicket(binding.adultTicketTv)
            sendResult(seatNumber, ADULT)
        }
        binding.childTicketTv.setOnClickListener {
            onSelectedTicket(binding.childTicketTv)
            sendResult(seatNumber, CHILD)
        }
        binding.studentTicketTv.setOnClickListener {
            onSelectedTicket(binding.studentTicketTv)
            sendResult(seatNumber, STUDENT)
        }
        binding.vipTicketTv.setOnClickListener {
            onSelectedTicket(binding.vipTicketTv)
            sendResult(seatNumber, VIP)
        }
    }

    private fun sendResult(seatNumber: String, ticketType: String?) {
        val targetFragment = targetFragment as? SeatSelectionFragment
        targetFragment?.updateSeatSelection(seatNumber, ticketType)
        dismiss()
    }

    private fun onSelectedTicket(selectedTextView: TextView) {
        binding.adultLinearLy.visibility = View.GONE
        binding.childLinearLy.visibility = View.GONE
        binding.studentLinearLy.visibility = View.GONE
        binding.vipLinearLy.visibility = View.GONE

        when (selectedTextView.id) {
            binding.adultTicketTv.id -> {
                binding.adultLinearLy.visibility = View.VISIBLE
                sharedViewModel.setSelectedTicketType(ADULT)
            }
            binding.childTicketTv.id -> {
                binding.childLinearLy.visibility = View.VISIBLE
                sharedViewModel.setSelectedTicketType(CHILD)
            }

            binding.studentTicketTv.id -> binding.studentLinearLy.visibility = View.VISIBLE
            binding.vipTicketTv.id -> binding.vipLinearLy.visibility = View.VISIBLE
        }

        when (selectedTextView.id) {
            binding.adultTicketTv.id -> sharedViewModel.setSelectedTicketType(ADULT)
            binding.childTicketTv.id -> sharedViewModel.setSelectedTicketType(CHILD)
            binding.studentTicketTv.id -> sharedViewModel.setSelectedTicketType(STUDENT)
            binding.vipTicketTv.id -> sharedViewModel.setSelectedTicketType(VIP)
        }

        binding.deselectSeatBtn.visibility = View.VISIBLE
    }

    private fun resetTicketSelection() {
        val seatNumber = arguments?.getString("seatNumber") ?: return
        sharedViewModel.setSelectedTicketType("")

        binding.adultLinearLy.visibility = View.VISIBLE
        binding.childLinearLy.visibility = View.VISIBLE
        binding.studentLinearLy.visibility = View.VISIBLE
        binding.vipLinearLy.visibility = View.VISIBLE
        binding.deselectSeatBtn.visibility = View.GONE

        sendResult(seatNumber, null)
        dismiss()
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

    companion object {
        fun newInstance(seatNumber: String, ticketType: String?): SelectSeatDialogFragment {
            val fragment = SelectSeatDialogFragment()
            val args = Bundle()
            args.putString("seatNumber", seatNumber)
            args.putString("ticketType", ticketType)
            fragment.arguments = args
            return fragment
        }
    }
}
