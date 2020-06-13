package com.easyinc.e_commerce.ui.catalog.adapter

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.ui.model.Product
import kotlinx.android.synthetic.main.product_raw.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogViewHolder(itemView: View, private val requestManager: RequestManager): RecyclerView.ViewHolder(itemView) {

    private val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,0f,1f)
    private val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,0f,1f)
    private val alpha = PropertyValuesHolder.ofFloat(View.ALPHA,0f,1f)

    fun bind(product: Product,clickListener: (Product) -> Unit,favClickListener: (Product) -> Unit){

        val isProductFavourite = product.isFavourite!!
        val isNew = product.isNew!!
        val favIcon  = if (isProductFavourite) R.drawable.ic_filled_favorite else R.drawable.ic_favorite

        itemView.product_is_new.visibility = if (isNew) View.VISIBLE else View.INVISIBLE
        itemView.product_to_favourite.setImageResource(favIcon)
        itemView.product_price.text = product.price.plus(" P")
        itemView.product_title.text = product.title

        requestManager.load(product.image)
            .error(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.details_image)

        itemView.product_to_favourite.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {

                ObjectAnimator.ofPropertyValuesHolder(it,scaleX,scaleY,alpha).apply {
                    interpolator = OvershootInterpolator()
                    doOnEnd {favClickListener(product) }
                }.setDuration(700L).start()
            }
        }

        itemView.setOnClickListener {
            clickListener(product)
        }

    }

}