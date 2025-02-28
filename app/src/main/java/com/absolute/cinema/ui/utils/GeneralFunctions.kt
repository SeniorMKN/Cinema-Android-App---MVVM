package com.absolute.cinema.ui.utils

import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.absolute.cinema.R

fun Fragment.onBackPressed() {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
            }
        })
}

fun Fragment.setupDialogMargins(view: View) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.margin_16dp)
    layoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_16dp)
    view.layoutParams = layoutParams
}