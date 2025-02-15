package com.absolute.cinema.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentHomeBinding
import com.absolute.cinema.ui.adapters.RecyclerViewAdapter
import com.absolute.cinema.data.model.MovieItemModel
import com.absolute.cinema.ui.login.LoginDialogFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemList: ArrayList<MovieItemModel>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        initRecyclerView()
    }

    private fun setupView() {

        binding.logo.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_tabsMovieFragment)
        }
        binding.loginButton.setOnClickListener {
            LoginDialogFragment().show(parentFragmentManager, "LoginDialog")
        }
    }

    private fun initRecyclerView() {
        itemList = arrayListOf(
            MovieItemModel(
                R.drawable.logo, "8.1", "Second Movie Title",
                "Second Movie Category", R.drawable.logo, "7.9",
                "Movie Title", "Movie Category"
            ),
            MovieItemModel(
                R.drawable.logo, "8.1", "Third Movie Title",
                "Third Movie Category", R.drawable.logo, "7.9",
                "Fourth Movie Title", "Fourth Movie Category"
            )
        )

        recyclerViewAdapter = RecyclerViewAdapter(itemList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}