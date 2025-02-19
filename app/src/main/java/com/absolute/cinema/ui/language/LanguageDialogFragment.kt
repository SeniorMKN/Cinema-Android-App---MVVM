package com.absolute.cinema.ui.language

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentLanguageDialogBinding

class LanguageDialogFragment : DialogFragment() {

    private var _binding: FragmentLanguageDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.kazakoLanguageTv.setOnClickListener { setSelectedSortOption(binding.checkKazakoIv) }
        binding.russianLanguageTv.setOnClickListener { setSelectedSortOption(binding.checkRussianIv) }
        binding.englishLanguageTv.setOnClickListener { setSelectedSortOption(binding.checkEnglishIv) }

    }

    private fun setSelectedSortOption(selectedCheck: View) {

        binding.checkKazakoIv.visibility = View.INVISIBLE
        binding.checkRussianIv.visibility = View.INVISIBLE
        binding.checkEnglishIv.visibility = View.INVISIBLE

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