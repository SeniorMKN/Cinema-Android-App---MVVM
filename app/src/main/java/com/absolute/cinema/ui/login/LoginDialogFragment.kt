package com.absolute.cinema.ui.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentLoginDialogBinding

class LoginDialogFragment : DialogFragment() {

    private var _binding: FragmentLoginDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueBtn.setOnClickListener {
            binding.apply {
                accessTv.text = getString(R.string.enter_the_password_from_the_sms)
                continueBtn.text = getString(R.string.login)

                phoneNumber.visibility = View.GONE
                linearLyCodeBox.visibility = View.VISIBLE
                changeNumberTv.visibility = View.VISIBLE
                resendTv.visibility = View.VISIBLE
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