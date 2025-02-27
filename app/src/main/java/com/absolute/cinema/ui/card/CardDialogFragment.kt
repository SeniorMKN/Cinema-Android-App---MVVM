package com.absolute.cinema.ui.card

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentCardDialogBinding
import com.absolute.cinema.ui.utils.UiColor

class CardDialogFragment : DialogFragment() {

    private var _binding: FragmentCardDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBankCardBtn.apply {
            isEnabled = false
            setBackgroundColor(UiColor.brownColor)
        }
        setupListeners()
        setupDialogMargins(view)
    }

    private fun setupListeners() {

        val inputTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val cardNumber = binding.cardNumberEt.text.toString().trim()
                val cardMonth = binding.monthEt.text.toString().trim()
                val cardCvc = binding.cvcEt.text.toString().trim()
                val isValid = cardNumber.length == 16 && cardMonth.length == 2 && cardCvc.length == 3
                val orangeColor = requireContext().getColor(R.color.orange)

                binding.addBankCardBtn.apply {
                    isEnabled = isValid
                    setBackgroundColor(if (isValid) orangeColor else UiColor.brownColor)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.apply {
            cardNumberEt.addTextChangedListener(inputTextWatcher)
            monthEt.addTextChangedListener(inputTextWatcher)
            cvcEt.addTextChangedListener(inputTextWatcher)
        }
    }

    private fun setupDialogMargins(view: View) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        layoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        view.layoutParams = layoutParams
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