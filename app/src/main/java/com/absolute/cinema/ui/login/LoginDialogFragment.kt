package com.absolute.cinema.ui.login

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentLoginDialogBinding

class LoginDialogFragment : DialogFragment() {
    private var _binding: FragmentLoginDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentLoginDialogBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_login_dialog) // Set the custom view
            .setCancelable(false) // Make dialog non-cancelable (optional)
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginCv.setOnClickListener {
            Log.i("LOGIN_CV", "login cv clicked")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
