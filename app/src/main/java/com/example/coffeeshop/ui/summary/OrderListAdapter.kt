package com.example.coffeeshop.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.R
import com.example.coffeeshop.data.model.Order
import com.example.coffeeshop.databinding.SummaryItemBinding
import com.example.coffeeshop.utility.loadImage

class OrderListAdapter : ListAdapter<Order, OrderListAdapter.ViewHolder>(OrderDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        SummaryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class OrderDiffCallBack : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = currentList[position]
        holder.bind(order)
    }

    class ViewHolder(private val binding: SummaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Order) {
            val total = data.price * data.quantity
            binding.apply {
                ivCoffee.loadImage(data.imageUrl)
                tvName.text = data.name
                tvPrice.text = root.context.getString(R.string.currency, data.price.toString())
                tvQuantity.text =
                    root.context.getString(R.string.multiply, data.quantity.toString())
                tvTotal.text = root.context.getString(R.string.currency, total.toString())
            }
        }
    }
}