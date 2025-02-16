package com.absolute.cinema.ui.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.absolute.cinema.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }
}