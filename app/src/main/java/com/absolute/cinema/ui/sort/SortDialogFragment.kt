package com.absolute.cinema.ui.sort

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.databinding.FragmentSortDialogBinding

class SortDialogFragment : DialogFragment() {

    private var _binding: FragmentSortDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.timeLinearLy.setOnClickListener { setSelectedSortOption(binding.checkTimeIv) }
        binding.distanceLinearLy.setOnClickListener { setSelectedSortOption(binding.checkDistanceIv) }
        binding.priceLinearLy.setOnClickListener { setSelectedSortOption(binding.checkPriceIv) }

        binding.ascendingLinearLy.setOnClickListener { setSelectedOrderOption(binding.checkAscendingIv) }
        binding.descendingLinearLy.setOnClickListener { setSelectedOrderOption(binding.checkDescendingIv) }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setSelectedSortOption(selectedCheck: View) {

        binding.checkTimeIv.visibility = View.INVISIBLE
        binding.checkDistanceIv.visibility = View.INVISIBLE
        binding.checkPriceIv.visibility = View.INVISIBLE

        selectedCheck.visibility = View.VISIBLE
    }

    private fun setSelectedOrderOption(selectedCheck: View) {

        binding.checkAscendingIv.visibility = View.INVISIBLE
        binding.checkDescendingIv.visibility = View.INVISIBLE

        selectedCheck.visibility = View.VISIBLE
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