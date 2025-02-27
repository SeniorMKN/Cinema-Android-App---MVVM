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
import com.absolute.cinema.ui.utils.UiColor

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

        binding.sortApplyBtn.apply {
            isEnabled = false
            setBackgroundColor(UiColor.brownColor)
        }

        binding.timeLinearLy.setOnClickListener { setSelectedSortOption(binding.checkTimeIv) }
        binding.distanceLinearLy.setOnClickListener { setSelectedSortOption(binding.checkDistanceIv) }
        binding.priceLinearLy.setOnClickListener { setSelectedSortOption(binding.checkPriceIv) }

        binding.ascendingLinearLy.setOnClickListener { setSelectedOrderOption(binding.checkAscendingIv) }
        binding.descendingLinearLy.setOnClickListener { setSelectedOrderOption(binding.checkDescendingIv) }

        super.onViewCreated(view, savedInstanceState)

        setupDialogMargins(view)
    }

    private fun setupDialogMargins(view: View) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        layoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        view.layoutParams = layoutParams
    }

    private fun setSelectedSortOption(selectedCheck: View) {

        binding.checkTimeIv.visibility = View.INVISIBLE
        binding.checkDistanceIv.visibility = View.INVISIBLE
        binding.checkPriceIv.visibility = View.INVISIBLE

        selectedCheck.visibility = View.VISIBLE

        isSortSelected = true
        enableApplyButton()
    }

    private fun setSelectedOrderOption(selectedCheck: View) {

        binding.checkAscendingIv.visibility = View.INVISIBLE
        binding.checkDescendingIv.visibility = View.INVISIBLE

        selectedCheck.visibility = View.VISIBLE

        isOrderSelected = true
        enableApplyButton()
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