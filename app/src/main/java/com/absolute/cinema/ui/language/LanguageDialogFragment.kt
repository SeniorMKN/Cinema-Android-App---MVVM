package com.absolute.cinema.ui.language

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentLanguageDialogBinding
import com.absolute.cinema.ui.utils.ProfileSharedPreferences
import com.absolute.cinema.ui.utils.UiUtils
import com.absolute.cinema.ui.utils.setupDialogMargins
import java.util.Locale

class LanguageDialogFragment : DialogFragment() {

    private var _binding: FragmentLanguageDialogBinding? = null
    private val binding get() = _binding!!
    private var selectedCheck: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupLanguages()
        setupDialogMargins(view)
    }

    private fun setupView() {
        binding.closeTv.setOnClickListener {
            dismiss()
        }

        binding.languageApplyBtn.apply {
            isEnabled = false
            setBackgroundColor(UiUtils.brownColor)
        }

        binding.languageApplyBtn.setOnClickListener {
            val newLocale = when (selectedCheck) {
                binding.checkFirstIv -> Locale("it")
                binding.checkThirdIv -> Locale("en")
                else -> Locale.getDefault()
            }

            ProfileSharedPreferences.saveLanguage(requireContext(), newLocale)
            requireActivity().recreate()
            dismiss()
        }
    }

    private fun setupLanguages() {
        binding.firstLanguageTv.setOnClickListener { setSelectedSortOption(binding.checkFirstIv) }
        binding.secondLanguageTv.setOnClickListener { setSelectedSortOption(binding.checkSecondIv) }
        binding.thirdLanguageTv.setOnClickListener { setSelectedSortOption(binding.checkThirdIv) }
    }

    private fun setSelectedSortOption(check: View) {
        binding.checkFirstIv.visibility = View.INVISIBLE
        binding.checkSecondIv.visibility = View.INVISIBLE
        binding.checkThirdIv.visibility = View.INVISIBLE

        check.visibility = View.VISIBLE

        selectedCheck = check

        binding.languageApplyBtn.apply {
            isEnabled = true
            setBackgroundColor(requireContext().getColor(R.color.orange))
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