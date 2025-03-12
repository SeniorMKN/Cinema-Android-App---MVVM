package com.absolute.cinema.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.absolute.cinema.R
import com.absolute.cinema.data.remote.MoviesSharedViewModel
import com.absolute.cinema.data.remote.response.MovieDto
import com.absolute.cinema.databinding.FragmentHomeBinding
import com.absolute.cinema.ui.adapters.RecyclerViewAdapter
import com.absolute.cinema.ui.city.CityDialogFragment
import com.absolute.cinema.ui.language.LanguageDialogFragment
import com.absolute.cinema.ui.login.LoginCallback
import com.absolute.cinema.ui.login.LoginDialogFragment
import com.absolute.cinema.ui.search.SearchCallBack
import com.absolute.cinema.ui.search.SearchDialogFragment
import com.absolute.cinema.ui.utils.ProfileSharedPreferences

class HomeFragment : Fragment(), LoginCallback, SearchCallBack {

    private var lastVisibleItemPosition = 0
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

        binding.progressBar.visibility = View.GONE

        login()
        initObserver()
        setupDialogs()
        setupListener()
        setupPopularMovies()
        onScrollView()
    }

    private fun onScrollView() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                lastVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && dy > 0) {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.fetchMovies(isPageScrolled = true)
                }
            }
        })
    }

    private fun setupPopularMovies() {
        viewModel.fetchMovies()
    }

    private fun setupListener() {
        binding.logo.setOnClickListener {
            viewModel.fetchMovies()
        }
    }

    private fun login() {
        val isLoggedIn = ProfileSharedPreferences.getIsLoggedIn(requireContext())
        if (isLoggedIn) {
            binding.loginButton.text = getString(R.string.profile)
            binding.loginButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        } else {
            binding.loginButton.text = getString(R.string.login)
            binding.loginButton.setOnClickListener {
                val loginDialogFragment = LoginDialogFragment()
                loginDialogFragment.setLoginCallback(this)
                loginDialogFragment.show(parentFragmentManager, "LoginDialog")
            }
        }
    }

    override fun onLoginSuccess(result: Boolean) {
        if (result) {
            binding.loginButton.text = getString(R.string.profile)
            binding.loginButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }
    }

    private fun initObserver() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { moviesList ->
            setupRecyclerView(moviesList)
            binding.progressBar.visibility = View.GONE
        }

        viewModel.searchMoviesLiveData.observe(viewLifecycleOwner) { searchedMoviesList ->
            setupRecyclerView(searchedMoviesList)
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun setupDialogs() {
        binding.positionName.setOnClickListener {
            CityDialogFragment().show(parentFragmentManager, "PositionDialog")
        }

        binding.languageName.setOnClickListener {
            LanguageDialogFragment().show(parentFragmentManager, "LanguageDialog")
        }

        binding.searchIv.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment()
            searchDialogFragment.searchCallBack = this
            searchDialogFragment.show(parentFragmentManager, "SearchDialog")
        }
    }

    private fun setupRecyclerView(moviesList: List<MovieDto>) {
        recyclerViewAdapter = RecyclerViewAdapter(moviesList, sharedViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(lastVisibleItemPosition, 0)
    }

    override fun onSearchMovieTitle(movieTitle: String) {
        Log.i("SEARCH", movieTitle)

        viewModel.searchMovies(movieTitle)
        setupRecyclerView(emptyList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}