package com.absolute.cinema.ui.login

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentLoginDialogBinding
import com.absolute.cinema.ui.utils.UiUtils
import com.absolute.cinema.ui.utils.setupDialogMargins

class LoginDialogFragment : DialogFragment() {

    private var _binding: FragmentLoginDialogBinding? = null
    private val binding get() = _binding!!
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()

        setupDialogMargins(view)
    }

    private fun setupView() {

        binding.closeTv.setOnClickListener {
            dismiss()
        }

        binding.continueBtn.apply {
            isEnabled = false
            setBackgroundColor(UiUtils.brownColor)
        }

        binding.resendTv.setOnClickListener {
            startResendTimer()
        }

        binding.continueBtn.setOnClickListener {
            binding.apply {
                accessTv.text = getString(R.string.enter_the_password_from_the_sms)
                continueBtn.text = getString(R.string.login)

                phoneNumber.visibility = View.GONE
                linearLyCodeBox.visibility = View.VISIBLE
                changeNumberTv.visibility = View.VISIBLE
                resendTv.visibility = View.VISIBLE

                continueBtn.isEnabled = false
                continueBtn.setBackgroundColor(UiUtils.brownColor)
            }
        }

        binding.changeNumberTv.setOnClickListener {
            binding.apply {
                accessTv.text = getString(R.string.access_to_purchased_tickets)
                continueBtn.text = getString(R.string.continue_btn)

                phoneNumber.visibility = View.VISIBLE
                linearLyCodeBox.visibility = View.GONE
                changeNumberTv.visibility = View.GONE
                resendTv.visibility = View.GONE
            }
        }
    }

    private fun setupListeners() {

        val inputTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val phoneNumber = binding.phoneNumber.text.toString().trim()
                val isValid = phoneNumber.length in 8..11
                val orangeColor = requireContext().getColor(R.color.orange)

                binding.continueBtn.apply {
                    isEnabled = isValid
                    setBackgroundColor(if (isValid) orangeColor else UiUtils.brownColor)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.apply {
            phoneNumber.addTextChangedListener(inputTextWatcher)
            binding.firstPinEt.requestFocus()

            val pinTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) {
                        when {
                            binding.firstPinEt.hasFocus() -> binding.secondPinEt.requestFocus()
                            binding.secondPinEt.hasFocus() -> binding.thirdPinEt.requestFocus()
                            binding.thirdPinEt.hasFocus() -> binding.fourthPinEt.requestFocus()
                        }
                    } else if (s?.isEmpty() == true) {
                        when {
                            binding.fourthPinEt.hasFocus() -> binding.thirdPinEt.requestFocus()
                            binding.thirdPinEt.hasFocus() -> binding.secondPinEt.requestFocus()
                            binding.secondPinEt.hasFocus() -> binding.firstPinEt.requestFocus()
                        }
                    }
                    toggleContinueButton()
                }

                override fun afterTextChanged(s: Editable?) {}
            }

            binding.firstPinEt.addTextChangedListener(pinTextWatcher)
            binding.secondPinEt.addTextChangedListener(pinTextWatcher)
            binding.thirdPinEt.addTextChangedListener(pinTextWatcher)
            binding.fourthPinEt.addTextChangedListener(pinTextWatcher)

            binding.secondPinEt.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && binding.firstPinEt.text.isNullOrEmpty()) {
                    binding.firstPinEt.requestFocus()
                }
            }
            binding.thirdPinEt.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && binding.secondPinEt.text.isNullOrEmpty()) {
                    binding.secondPinEt.requestFocus()
                }
            }
            binding.fourthPinEt.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && binding.thirdPinEt.text.isNullOrEmpty()) {
                    binding.thirdPinEt.requestFocus()
                }
            }
        }
    }

    private fun toggleContinueButton() {
        val isAllFilled = binding.firstPinEt.text?.isNotEmpty() == true &&
                binding.secondPinEt.text?.isNotEmpty() == true &&
                binding.thirdPinEt.text?.isNotEmpty() == true &&
                binding.fourthPinEt.text?.isNotEmpty() == true

        val orangeColor = requireContext().getColor(R.color.orange)

        binding.continueBtn.apply {
            isEnabled = isAllFilled
            setBackgroundColor(if (isAllFilled) orangeColor else UiUtils.brownColor)
        }
    }


    private fun startResendTimer() {

        countDownTimer = object : CountDownTimer(59000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                if (_binding != null) {
                    binding.resendTv.text = getString(R.string.resend_in_seconds, secondsRemaining)
                }
            }

            override fun onFinish() {
                if (_binding != null) {
                    binding.resendTv.text = getString(R.string.resend_now)
                }
            }
        }.start()
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
        countDownTimer?.cancel()
        _binding = null
    }
}