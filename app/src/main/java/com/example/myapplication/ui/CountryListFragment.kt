package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.CountryListFragmentBinding
import com.example.myapplication.model.CountryItem
import com.example.myapplication.viewmodel.CountryListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private lateinit var binding: CountryListFragmentBinding
    private val viewModel: CountryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CountryListFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.items.observe(viewLifecycleOwner, ::onItemsLoaded)

        binding.swipeContainer.setOnRefreshListener(viewModel)
        return binding.root
    }

    private fun onItemsLoaded(list: List<CountryItem>?) {
        // Check if list is empty or not, Ask user to refresh if data is not present.
        if (!list.isNullOrEmpty()) return
        Snackbar.make(binding.root, R.string.there_is_no_data, Snackbar.LENGTH_SHORT)
            .setAction(R.string.retry) {
                binding.swipeContainer.isRefreshing = true
                viewModel.onRefresh()
            }.show()
    }
}