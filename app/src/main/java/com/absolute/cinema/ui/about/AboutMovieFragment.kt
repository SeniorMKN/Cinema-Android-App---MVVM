package com.absolute.cinema.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.absolute.cinema.databinding.FragmentAboutMovieBinding
import com.absolute.cinema.ui.tabs.TabsMovieFragment

class AboutMovieFragment : Fragment() {

    private var _binding: FragmentAboutMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectSessionBtn.setOnClickListener {
            (requireParentFragment() as? TabsMovieFragment)?.navigateToSeatSelection()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
