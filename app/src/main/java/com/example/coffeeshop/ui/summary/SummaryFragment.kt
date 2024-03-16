package com.example.coffeeshop.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffeeshop.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding

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
        if (data.isNotBlank()){

        }
    }

}