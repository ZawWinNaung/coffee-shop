package com.example.coffeeshop.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffeeshop.widgets.LoadingDialog

abstract class BaseFragment: Fragment() {
    lateinit var loadingDialog: LoadingDialog
}