package com.absolute.cinema.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.data.remote.respond.MovieDto
import com.absolute.cinema.databinding.FragmentHomeBinding
import com.absolute.cinema.ui.adapters.RecyclerViewAdapter
import com.absolute.cinema.ui.city.CityDialogFragment
import com.absolute.cinema.ui.language.LanguageDialogFragment
import com.absolute.cinema.ui.login.LoginDialogFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

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

        setupDialogs()
        initObserver()

        viewModel.fetchMovies()
    }

    private fun initObserver() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { moviesList ->
            setupRecyclerView(moviesList)
        }
    }

    private fun setupDialogs() {
        binding.loginButton.setOnClickListener {
            LoginDialogFragment().show(parentFragmentManager, "LoginDialog")
        }

        binding.positionName.setOnClickListener {
            CityDialogFragment().show(parentFragmentManager, "PositionDialog")
        }

        binding.languageName.setOnClickListener {
            LanguageDialogFragment().show(parentFragmentManager, "LanguageDialog")
        }
    }

    private fun setupRecyclerView(moviesList: List<MovieDto>) {
        recyclerViewAdapter = RecyclerViewAdapter(moviesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
