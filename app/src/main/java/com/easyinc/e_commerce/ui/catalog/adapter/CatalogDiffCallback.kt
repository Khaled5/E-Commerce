package com.easyinc.e_commerce.ui.catalog.adapter

import androidx.recyclerview.widget.DiffUtil
import com.easyinc.e_commerce.ui.model.Product

class CatalogDiffCallback(private val oldList: List<Product>, private val newList: List<Product>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val o = oldList[oldItemPosition]
        val n = newList[newItemPosition]

        return o == n
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}