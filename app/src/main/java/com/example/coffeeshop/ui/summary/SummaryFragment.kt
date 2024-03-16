package com.example.coffeeshop.ui.summary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.data.model.Order
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.databinding.FragmentSummaryBinding
import com.example.coffeeshop.utility.toObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding

    private lateinit var orderListAdapter: OrderListAdapter

    private val data by lazy {
        SummaryFragmentArgs.fromBundle(requireArguments()).data
    }

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
        orderListAdapter = OrderListAdapter()

        setUpView()

        if (data.isNotBlank()) {
            val orderList: List<Order>? = data.toObject()
            orderList?.let {
                orderListAdapter.submitList(it)
            }
        }
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
        }
    }

}