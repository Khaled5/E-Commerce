package com.easyinc.e_commerce.ui.favourite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.ui.model.Product
import kotlinx.android.synthetic.main.favourite_raw.view.*
import kotlinx.android.synthetic.main.product_raw.view.*

class FavouriteViewHolder(itemView: View, private val requestManager: RequestManager): RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product,clickListener: (Product) -> Unit,removeFavClickListener: (Product) -> Unit,cartClickListener: () -> Unit){
        itemView.is_favourite_new.visibility = if (product.isNew!!) View.VISIBLE else View.INVISIBLE
        itemView.favorite_price.text = product.price.plus(" P")
        itemView.favorite_title.text = product.title
        requestManager.load(product.image)
            .placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.favorite_image)

        itemView.favorite_remove.setOnClickListener {
            removeFavClickListener(product)
        }

        itemView.setOnClickListener {
            clickListener(product)
        }

        itemView.favorite_add_to_cart.setOnClickListener {
            cartClickListener()
        }

    }

}