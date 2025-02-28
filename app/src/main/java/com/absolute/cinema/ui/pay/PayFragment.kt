package com.absolute.cinema.ui.pay

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentPayBinding
import com.absolute.cinema.ui.utils.UiUtils
import com.absolute.cinema.ui.utils.UiUtils.initGridLayout
import com.absolute.cinema.ui.utils.onBackPressed

class PayFragment : Fragment() {

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPressed()
        setupView()
        setupListeners()

        initGridLayout(binding.gridLy, requireContext())
    }

    private fun setupListeners() {

        val inputTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val phoneNumber = binding.phoneNumberEt.text.toString().trim()
                val isValid = phoneNumber.length in 8..11
                val orangeColor = requireContext().getColor(R.color.orange)

                binding.payContinueBtn.apply {
                    isEnabled = isValid
                    setBackgroundColor(if (isValid) orangeColor else UiUtils.brownColor)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.apply {
            phoneNumberEt.addTextChangedListener(inputTextWatcher)
        }
    }

    private fun setupView() {

        binding.payContinueBtn.apply {
            isEnabled = false
            setBackgroundColor(UiUtils.brownColor)
        }

        binding.payContinueBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_payFragment_to_ticketFragment)
        }

        binding.backArrowTv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}