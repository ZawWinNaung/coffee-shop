package com.example.coffeeshop.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.R
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.databinding.CoffeeItemBinding
import com.example.coffeeshop.utility.loadImage

class ProductListAdapter(private val itemClickListener: ((Product, Int, Boolean) -> Unit)?) :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        CoffeeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), itemClickListener
    )

    private class ProductDiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = currentList[position]
        holder.bind(product)
    }

    class ProductViewHolder(
        private val binding: CoffeeItemBinding,
        private val itemClickListener: ((Product, Int, Boolean) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Product) {
            binding.apply {
                ivCoffee.loadImage(data.imageUrl ?: "")
                tvName.text = data.name
                tvAmount.text = root.context.getString(R.string.currency, data.price.toString())
                cbSelect.setOnCheckedChangeListener { _, b ->
                    itemClickListener?.invoke(data, numberPicker.value, b)
                }
                numberPicker.setValueChangedListener { value, action ->
                    cbSelect.isChecked = false
                }
            }
        }
    }


}