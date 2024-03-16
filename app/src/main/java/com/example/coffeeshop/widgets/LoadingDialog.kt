package com.example.coffeeshop.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.coffeeshop.R

class LoadingDialog(private val context: Context) {

    private var progress = Dialog(context)

    init {
        progress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progress.setContentView(R.layout.dialog_loading)
        progress.setCancelable(false)
    }

    public inline var isShow: Boolean
        get() = isShowing()
        set(value) {
            if (value) {
                show()
            } else {
                dismiss()
            }
        }

    fun isShowing(): Boolean = progress.isShowing

    fun show() {
        progress.show()
    }

    fun dismiss() {
        progress.dismiss()
    }

}