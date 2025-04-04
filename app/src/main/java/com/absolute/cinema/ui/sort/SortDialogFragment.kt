package com.absolute.cinema.ui.sort

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentSortDialogBinding
import com.absolute.cinema.ui.utils.UiUtils
import com.absolute.cinema.ui.utils.setupDialogMargins

class SortDialogFragment : DialogFragment() {

    private var _binding: FragmentSortDialogBinding? = null
    private val binding get() = _binding!!
    private var isSortSelected = false
    private var isOrderSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupSortOption()
        setupOrderOption()
        setupDialogMargins(view)

    }

    private fun setupView() {
        binding.apply {
            closeTv.setOnClickListener {
                dismiss()
            }

            sortApplyBtn.apply {
                isEnabled = false
                setBackgroundColor(UiUtils.brownColor)
            }
        }
    }

    private fun setupOrderOption() {
        binding.apply {
            ascendingLinearLy.setOnClickListener { setSelectedOrderOption(binding.checkAscendingIv) }
            descendingLinearLy.setOnClickListener { setSelectedOrderOption(binding.checkDescendingIv) }
        }
    }

    private fun setupSortOption() {
        binding.apply {
            timeLinearLy.setOnClickListener { setSelectedSortOption(binding.checkTimeIv) }
            distanceLinearLy.setOnClickListener { setSelectedSortOption(binding.checkDistanceIv) }
        }
    }

    private fun setSelectedSortOption(selectedCheck: View) {
        binding.apply {
            checkTimeIv.visibility = View.INVISIBLE
            checkDistanceIv.visibility = View.INVISIBLE

            selectedCheck.visibility = View.VISIBLE

            isSortSelected = true
            enableApplyButton()
        }
    }

    private fun setSelectedOrderOption(selectedCheck: View) {
        binding.apply {
            checkAscendingIv.visibility = View.INVISIBLE
            checkDescendingIv.visibility = View.INVISIBLE

            selectedCheck.visibility = View.VISIBLE

            isOrderSelected = true
            enableApplyButton()
        }
    }

    private fun enableApplyButton() {
        if (isSortSelected && isOrderSelected) {
            binding.sortApplyBtn.apply {
                isEnabled = true
                setBackgroundColor(requireContext().getColor(R.color.orange))
            }
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

}