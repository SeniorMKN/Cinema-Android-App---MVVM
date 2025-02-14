package com.absolute.cinema.ui.ticket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.setMargins
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayout = binding.gridLy
        gridLayout.columnCount = 14
        gridLayout.removeAllViews()

        for (i in 0 until 14) {
            val circleView = ImageView(requireContext()).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 48
                    height = 48
                    setMargins(8)
                }
                setImageResource(R.drawable.circle)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            gridLayout.addView(circleView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}