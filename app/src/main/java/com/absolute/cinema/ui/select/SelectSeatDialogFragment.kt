package com.absolute.cinema.ui.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.databinding.FragmentSelectSeatDialogBinding

class SelectSeatDialogFragment : DialogFragment() {

    private var _binding: FragmentSelectSeatDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectSeatDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
}