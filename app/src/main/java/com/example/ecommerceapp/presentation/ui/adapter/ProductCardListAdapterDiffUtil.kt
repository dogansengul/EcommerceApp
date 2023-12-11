package com.example.ecommerceapp.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommerceapp.business.Product

class ProductCardListAdapterDiffUtil(
    private val newData: ArrayList<Product>,
    private val oldData: ArrayList<Product>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newData[newItemPosition].id == oldData[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newData[newItemPosition] == oldData[oldItemPosition]
    }
}