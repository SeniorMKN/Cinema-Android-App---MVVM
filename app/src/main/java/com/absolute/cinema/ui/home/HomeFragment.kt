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
import com.absolute.cinema.ui.login.LoginDialogFragment
import com.absolute.cinema.ui.login.LoginDialogViewModel
import com.absolute.cinema.ui.utils.ProfileSharedPreferences

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MoviesSharedViewModel by activityViewModels()
    private val loginDialogFragment: LoginDialogViewModel by activityViewModels()

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

        loginDialogFragment.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                binding.loginButton.text = getString(R.string.profile)
                binding.loginButton.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                }
            } else {
                binding.loginButton.text = getString(R.string.login)
                binding.loginButton.setOnClickListener {
                    LoginDialogFragment().show(parentFragmentManager, "LoginDialog")
                }
            }
        }

    }

    private fun setupDialogs() {

        val isLoggedIn = isUserLoggedIn()

        if (isLoggedIn) {
            loginDialogFragment.setLoggedIn(true)
        } else {
            loginDialogFragment.setLoggedIn(false)
        }

        binding.positionName.setOnClickListener {
            CityDialogFragment().show(parentFragmentManager, "PositionDialog")
        }

        binding.languageName.setOnClickListener {
            LanguageDialogFragment().show(parentFragmentManager, "LanguageDialog")
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val savedPhoneNumber = ProfileSharedPreferences.getPhoneNumber(requireContext())
        val savedPin = ProfileSharedPreferences.getPin(requireContext())
        return !savedPhoneNumber.isNullOrEmpty() && savedPin == "1111"
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
