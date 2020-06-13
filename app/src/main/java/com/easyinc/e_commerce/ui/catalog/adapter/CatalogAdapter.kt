package com.easyinc.e_commerce.ui.catalog.adapter

import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.common.extentions.inflate
import com.easyinc.e_commerce.ui.model.Product
import javax.inject.Inject

class CatalogAdapter @Inject constructor(@Nullable val requestManager: RequestManager): RecyclerView.Adapter<CatalogViewHolder>() {

    private var catalogList = mutableListOf<Product>()

    internal var clickListener: (Product) -> Unit = {_ -> }
    internal var favouriteClickListener: (Product) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatalogViewHolder(parent.inflate(R.layout.product_raw),requestManager)

    override fun getItemCount() = catalogList.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) = holder.bind(catalogList[position], clickListener,favouriteClickListener)

    fun submitList(list: List<Product>) {
        val catalogDiffCallback = CatalogDiffCallback(catalogList, list)
        val diffResult = DiffUtil.calculateDiff(catalogDiffCallback)
        catalogList.clear()
        catalogList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

}