package com.example.coffeeshop.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.R
import com.example.coffeeshop.core.BaseFragment
import com.example.coffeeshop.data.mapper.ProductsMapper.mapToProduct
import com.example.coffeeshop.data.model.Order
import com.example.coffeeshop.data.remote.request.OrderRequestModel
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.databinding.FragmentSummaryBinding
import com.example.coffeeshop.utility.toObject
import com.example.coffeeshop.widgets.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : BaseFragment() {

    private lateinit var binding: FragmentSummaryBinding

    private lateinit var orderListAdapter: OrderListAdapter

    private val viewModel: SummaryViewModel by viewModels()

    private val data by lazy {
        SummaryFragmentArgs.fromBundle(requireArguments()).data
    }

    private var orderList: List<Order>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(requireContext())
        orderListAdapter = OrderListAdapter()

        setUpView()
        observeLiveData()

        if (data.isNotBlank()) {
            orderList = data.toObject()
            orderList?.let {
                orderListAdapter.submitList(it)
                calculateNetTotal(it)
            }
        }
    }

    private fun calculateNetTotal(it: List<Order>) {
        var netTotal = 0
        it.forEach { order ->
            netTotal += (order.price * order.quantity)
        }
        binding.tvNetTotal.text =
            requireContext().getString(R.string.currency, netTotal.toString())
    }

    private fun setUpView() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            rvOrderList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = orderListAdapter
            }
            btnConfirm.setOnClickListener {
                val productList = mutableListOf<Product>()
                orderList?.let {
                    productList.addAll(mapToProduct(it))
                    viewModel.makeOrder(
                        OrderRequestModel(
                            products = productList,
                            deliveryAddress = binding.etAddress.text.toString()
                        )
                    )
                }
            }
            etAddress.addTextChangedListener {
                tilAddress.error = null
            }
        }
    }

    private fun observeLiveData() {
        viewModel.addressError.observe(viewLifecycleOwner) {
            binding.tilAddress.error = it
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            loadingDialog.isShow = it
        }
        viewModel.success.observe(viewLifecycleOwner) {
            findNavController().navigate(SummaryFragmentDirections.actionSummaryFragmentToSuccessFragment())
        }
    }

}