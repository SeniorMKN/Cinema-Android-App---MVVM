package com.absolute.cinema.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.databinding.FragmentHomeBinding
import com.absolute.cinema.ui.adapters.RecyclerViewAdapter
import com.absolute.cinema.ui.data.RecyclerItemModel
import com.absolute.cinema.ui.login.LoginDialogFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemList: ArrayList<RecyclerItemModel>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemList = arrayListOf(
            RecyclerItemModel(
                R.drawable.cover_film, "Film Title 1", "Category 1",
                R.drawable.cover_film, "Film Title 2", "Category 2"
            ),
            RecyclerItemModel(
                R.drawable.cover_film, "Film Title 3", "Category 3",
                R.drawable.cover_film, "Film Title 4", "Category 4"
            )
        )
        recyclerViewAdapter = RecyclerViewAdapter(itemList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.logo.setOnClickListener {
            LoginDialogFragment().show(parentFragmentManager, "LoginDialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}