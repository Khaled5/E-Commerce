package com.easyinc.e_commerce.ui.favourite.adapter

import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.common.extentions.inflate
import com.easyinc.e_commerce.ui.catalog.adapter.CatalogDiffCallback
import com.easyinc.e_commerce.ui.model.Product
import javax.inject.Inject

class FavouriteAdapter @Inject constructor(@Nullable val requestManager: RequestManager): RecyclerView.Adapter<FavouriteViewHolder>() {

    private var favouritesList = mutableListOf<Product>()

    internal var clickListener: (Product) -> Unit = {_ -> }
    internal var cartClickListener: () -> Unit = {}
    internal var removeFavClickListener: (Product) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavouriteViewHolder(parent.inflate(R.layout.favourite_raw),requestManager)

    override fun getItemCount() = favouritesList.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) = holder.bind(favouritesList[position], clickListener,
        removeFavClickListener,cartClickListener)

    fun submitList(list: List<Product>){
        val catalogDiffCallback = CatalogDiffCallback(favouritesList,list)
        val diffResult = DiffUtil.calculateDiff(catalogDiffCallback)
        favouritesList.clear()
        favouritesList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

}