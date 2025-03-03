package com.absolute.cinema.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.data.remote.response.MovieDto
import com.absolute.cinema.databinding.FragmentHomeBinding
import com.absolute.cinema.ui.adapters.RecyclerViewAdapter
import com.absolute.cinema.ui.city.CityDialogFragment
import com.absolute.cinema.ui.language.LanguageDialogFragment
import com.absolute.cinema.ui.login.LoginCallback
import com.absolute.cinema.ui.login.LoginDialogFragment
import com.absolute.cinema.ui.utils.ProfileSharedPreferences


class HomeFragment : Fragment(), LoginCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()

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

        val isLoggedIn = ProfileSharedPreferences.getIsLoggedIn(requireContext())
        if (isLoggedIn) {
            binding.loginButton.text = "Profile"
            binding.loginButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        } else {
            binding.loginButton.text = "Log in"
            binding.loginButton.setOnClickListener {
                val loginDialogFragment = LoginDialogFragment()
                loginDialogFragment.setLoginCallback(this)
                loginDialogFragment.show(parentFragmentManager, "LoginDialog")
            }
        }

        initObserver()
        setupDialogs()
        viewModel.fetchMovies()
    }

    override fun onLoginSuccess(result: Boolean) {
        if (result) {
            binding.loginButton.text = "Profile"
            binding.loginButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }
    }

    private fun initObserver() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { moviesList ->
            setupRecyclerView(moviesList)
        }
    }

    private fun setupDialogs() {
        binding.positionName.setOnClickListener {
            CityDialogFragment().show(parentFragmentManager, "PositionDialog")
        }

        binding.languageName.setOnClickListener {
            LanguageDialogFragment().show(parentFragmentManager, "LanguageDialog")
        }
    }

    private fun setupRecyclerView(moviesList: List<MovieDto>) {
        recyclerViewAdapter = RecyclerViewAdapter(moviesList, sharedViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}