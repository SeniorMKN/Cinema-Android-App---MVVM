package com.absolute.cinema.ui.ticket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentTicketBinding
import com.absolute.cinema.ui.utils.UiUtils

class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        UiUtils.initGridLayout(binding.gridLy, requireContext())
    }

    private fun setupView() {
        binding.yourTicketTv.setOnClickListener {
            it.findNavController().navigate(R.id.action_ticketFragment_to_profileFragment)
        }

        binding.closeTv.setOnClickListener {
            it.findNavController().navigate(R.id.action_ticketFragment_to_homeFragment)
        }

        binding.sendBtn.setOnClickListener {
            shareTicketInfo()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            })
    }

    private fun shareTicketInfo() {
        val ticketInfo = "I have a ticket for the movie! Check it out!"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, ticketInfo)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        if (sendIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
