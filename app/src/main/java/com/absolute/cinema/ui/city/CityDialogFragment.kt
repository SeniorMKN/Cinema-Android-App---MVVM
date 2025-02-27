package com.absolute.cinema.ui.city

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.absolute.cinema.R
import com.absolute.cinema.data.model.CityItemModel
import com.absolute.cinema.databinding.FragmentCityDialogBinding
import com.absolute.cinema.ui.adapters.CityRecyclerViewAdapter
import com.absolute.cinema.ui.utils.UiColor

class CityDialogFragment : DialogFragment() {

    private var _binding: FragmentCityDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemList: ArrayList<CityItemModel>
    private lateinit var recyclerViewAdapter: CityRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityApplyBtn.apply {
            isEnabled = false
            setBackgroundColor(UiColor.brownColor)
        }

        initRecyclerView()
        setupDialogMargins(view)
    }

    private fun setupDialogMargins(view: View) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        layoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_16dp)
        view.layoutParams = layoutParams
    }

    private fun initRecyclerView() {

        itemList = arrayListOf(
            CityItemModel("Almaty"),
            CityItemModel("Shymkent"),
            CityItemModel("Nur-Sultan"),
            CityItemModel("Karaganda"),
            CityItemModel("Kokshetau"),
            CityItemModel("Pavlodar"),
            CityItemModel("Oskemen"),
            CityItemModel("Semey"),
            CityItemModel("Kostanay"),
            CityItemModel("Oral")
        )

        recyclerViewAdapter = CityRecyclerViewAdapter(itemList) {
            binding.cityApplyBtn.isEnabled = true
            binding.cityApplyBtn.setBackgroundColor(requireContext().getColor(R.color.orange))
        }
        binding.cityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cityRecyclerView.adapter = recyclerViewAdapter

    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setDimAmount(0.1F)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.decorView?.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}