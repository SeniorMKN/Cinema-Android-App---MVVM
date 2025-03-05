package com.absolute.cinema.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.absolute.cinema.R
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.databinding.FragmentMovieTabsBinding
import com.absolute.cinema.ui.about.AboutMovieFragment
import com.absolute.cinema.ui.adapters.MyPagerAdapter
import com.absolute.cinema.ui.sessions.SessionsMovieFragment
import com.google.android.material.tabs.TabLayoutMediator

class TabsMovieFragment : Fragment() {

    private var _binding: FragmentMovieTabsBinding? = null
    private val binding get() = _binding!!

    private lateinit var myAdapter: MyPagerAdapter
    private var tabsArray = arrayOf("About", "Sessions")
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        myAdapter = MyPagerAdapter(childFragmentManager, lifecycle)
        myAdapter.addFragmentToList(AboutMovieFragment())
        myAdapter.addFragmentToList(SessionsMovieFragment())

        binding.viewPager.adapter = myAdapter

        TabLayoutMediator(
            binding.tablayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }


    private fun setupView() {
        //binding.movieTitleTv.text = sharedViewModel.selectedMovieTitle.value
    }

    private fun setupListeners() {
        binding.backArrowTv.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    // function used in about movie btn
    fun navigateToSeatSelection() {
        findNavController().navigate(R.id.action_tabsMovieFragment_to_seatSelectionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}