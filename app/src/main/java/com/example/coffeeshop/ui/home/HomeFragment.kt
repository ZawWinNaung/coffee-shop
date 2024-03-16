package com.example.coffeeshop.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeeshop.databinding.FragmentHomeBinding
import com.example.coffeeshop.utility.formatTime
import com.example.coffeeshop.widgets.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var loadingDialog: LoadingDialog

    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(requireContext())
        productListAdapter = ProductListAdapter {  }
        setUpView()
        observeLiveData()
    }

    private fun setUpView() {
        binding.apply {
            rvProducts.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = productListAdapter
            }
        }
    }

    private fun observeLiveData() {
        viewModel.loading.observe(viewLifecycleOwner) {
            loadingDialog.isShow = it
        }

        viewModel.storeInfo.observe(viewLifecycleOwner) {
            binding.tvShopName.text = it.name
            binding.tvRating.text = (it.rating ?: "-").toString()
            binding.tvOpen.text = it.openingTime.formatTime()
            binding.tvClose.text = it.closingTime.formatTime()
        }

        viewModel.productList.observe(viewLifecycleOwner) {
            productListAdapter.submitList(it)
        }
    }
}