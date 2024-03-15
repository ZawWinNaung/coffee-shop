package com.example.coffeeshop.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ApiServiceRepo
) : ViewModel() {

    fun getStoreInfo() {
        viewModelScope.launch {
//            val info = repo.getStoreInfo()
//            Log.d("#home", info.toString())
        }
    }
}