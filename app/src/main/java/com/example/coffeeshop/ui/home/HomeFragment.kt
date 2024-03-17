package com.example.coffeeshop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeeshop.core.BaseFragment
import com.example.coffeeshop.data.model.Order
import com.example.coffeeshop.databinding.FragmentHomeBinding
import com.example.coffeeshop.utility.formatTime
import com.example.coffeeshop.utility.toJson
import com.example.coffeeshop.widgets.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

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
        productListAdapter = ProductListAdapter { product, quantity, isSelect ->
            val order = Order(
                name = product.name ?: "",
                price = product.price ?: 0,
                imageUrl = product.imageUrl ?: "",
                quantity = quantity
            )
            if (isSelect) {
                viewModel.orderList.add(order)
            } else {
                viewModel.orderList.removeIf { it.name == order.name && it.price == order.price && it.imageUrl == order.imageUrl }
            }
        }
        setUpView()
        observeLiveData()
    }

    private fun setUpView() {
        binding.apply {
            rvProducts.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = productListAdapter
            }
            fabCheckOut.setOnClickListener {
                if (viewModel.orderList.isNotEmpty()) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToSummaryFragment(
                            data = viewModel.orderList.toJson()
                        )
                    )
                    viewModel.orderList.clear()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please choose the products first!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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