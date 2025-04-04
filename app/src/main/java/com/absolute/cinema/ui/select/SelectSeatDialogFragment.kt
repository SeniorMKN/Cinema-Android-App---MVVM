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

        binding.apply {
            setupView()
            deselectSeatBtn.visibility = View.GONE

            val seatNumber = arguments?.getString("seatNumber") ?: return
            val selectedTicketType = arguments?.getString("ticketType")
            setupListeners(seatNumber)

            when (selectedTicketType) {
                ADULT -> onSelectedTicket(adultTicketTv)
                CHILD -> onSelectedTicket(childTicketTv)
                STUDENT -> onSelectedTicket(studentTicketTv)
                VIP -> onSelectedTicket(vipTicketTv)
            }
        }
    }

    private fun setupView() {
        binding.apply {
            adultTicketPriceTv.text = sharedViewModel.getAdultPrice()
            childTicketPriceTv.text = sharedViewModel.getChildPrice()
            studentTicketPriceTv.text = sharedViewModel.getStudentPrice()
            vipTicketPriceTv.text = sharedViewModel.getVipPrice()
        }
    }

    private fun setupListeners(seatNumber: String) {
        binding.apply {
            closeTv.setOnClickListener {
                dismiss()
            }

            deselectSeatBtn.setOnClickListener {
                resetTicketSelection()
            }

            adultLinearLy.setOnClickListener {
                onSelectedTicket(adultTicketTv)
                sendResult(seatNumber, ADULT)
            }
            childTicketTv.setOnClickListener {
                onSelectedTicket(childTicketTv)
                sendResult(seatNumber, CHILD)
            }
            studentTicketTv.setOnClickListener {
                onSelectedTicket(studentTicketTv)
                sendResult(seatNumber, STUDENT)
            }
            vipTicketTv.setOnClickListener {
                onSelectedTicket(vipTicketTv)
                sendResult(seatNumber, VIP)
            }
        }
    }

    private fun sendResult(seatNumber: String, ticketType: String?) {
        val result = Bundle().apply {
            putString("seatNumber", seatNumber)
            putString("ticketType", ticketType)
        }
        parentFragmentManager.setFragmentResult("seatSelection", result)
        dismiss()
    }

    private fun onSelectedTicket(selectedTextView: TextView) {
        binding.apply {
            adultLinearLy.visibility = View.GONE
            childLinearLy.visibility = View.GONE
            studentLinearLy.visibility = View.GONE
            vipLinearLy.visibility = View.GONE

            when (selectedTextView.id) {
                adultTicketTv.id -> {
                    adultLinearLy.visibility = View.VISIBLE
                    sharedViewModel.setSelectedTicketType(ADULT)
                }

                childTicketTv.id -> {
                    childLinearLy.visibility = View.VISIBLE
                    sharedViewModel.setSelectedTicketType(CHILD)
                }

                studentTicketTv.id -> studentLinearLy.visibility = View.VISIBLE
                vipTicketTv.id -> vipLinearLy.visibility = View.VISIBLE
            }

            when (selectedTextView.id) {
                adultTicketTv.id -> sharedViewModel.setSelectedTicketType(ADULT)
                childTicketTv.id -> sharedViewModel.setSelectedTicketType(CHILD)
                studentTicketTv.id -> sharedViewModel.setSelectedTicketType(STUDENT)
                vipTicketTv.id -> sharedViewModel.setSelectedTicketType(VIP)
            }

            deselectSeatBtn.visibility = View.VISIBLE
        }
    }

    private fun resetTicketSelection() {
        binding.apply {
            val seatNumber = arguments?.getString("seatNumber") ?: return
            sharedViewModel.setSelectedTicketType("")

            adultLinearLy.visibility = View.VISIBLE
            childLinearLy.visibility = View.VISIBLE
            studentLinearLy.visibility = View.VISIBLE
            vipLinearLy.visibility = View.VISIBLE
            deselectSeatBtn.visibility = View.GONE

            sendResult(seatNumber, null)
            dismiss()
        }
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
