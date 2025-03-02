package com.absolute.cinema.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentMovieTabsBinding
import com.absolute.cinema.ui.about.AboutMovieFragment
import com.absolute.cinema.ui.adapters.MyPagerAdapter
import com.absolute.cinema.ui.sessions.SessionsMovieFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabsMovieFragment : Fragment() {

    private var _binding: FragmentMovieTabsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager2: ViewPager2
    private lateinit var myAdapter: MyPagerAdapter
    private lateinit var tabLayout: TabLayout
    private var tabsArray = arrayOf("About", "Sessions")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        argumentsPassed()

        binding.backArrowTv.setOnClickListener {
            findNavController().popBackStack()
        }

        tabLayout = binding.tablayout
        viewPager2 = binding.viewPager2
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        myAdapter = MyPagerAdapter(childFragmentManager, lifecycle)
        myAdapter.addFragmentToList(AboutMovieFragment())
        myAdapter.addFragmentToList(SessionsMovieFragment())

        viewPager2.adapter = myAdapter

        TabLayoutMediator(
            tabLayout,
            viewPager2
        ) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }

    fun argumentsPassed(){
        val movieTitle = arguments?.getString("movieTitle")
        binding.movieTitleTv.text = movieTitle
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