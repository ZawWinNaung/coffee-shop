package com.example.coffeeshop.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.FragmentSucccessBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SuccessFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSucccessBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSucccessBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDone.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, true)
        }
    }

}