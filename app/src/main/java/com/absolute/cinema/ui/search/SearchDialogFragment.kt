package com.absolute.cinema.ui.search

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.databinding.FragmentSearchDialogBinding

class SearchDialogFragment : DialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!
    var searchCallBack: SearchCallBack? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        binding.closeTv.setOnClickListener {
            dismiss()
        }

        binding.searchApplyBtn.setOnClickListener {
            val searchEt = binding.searchEt.text.toString()
            if (searchEt.isNotEmpty()) {
                searchCallBack?.onSearchMovieTitle(searchEt)
                dismiss()
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

interface SearchCallBack {
    fun onSearchMovieTitle(movieTitle: String)
}