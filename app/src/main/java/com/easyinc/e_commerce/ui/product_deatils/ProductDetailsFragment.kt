package com.easyinc.e_commerce.ui.product_deatils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.easyinc.e_commerce.MainActivity
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.common.base.BaseFragment
import com.easyinc.e_commerce.ui.model.Product
import kotlinx.android.synthetic.main.favourites_toolbar.*
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailsFragment : BaseFragment() {

    private lateinit var product: Product

    private var isProductFavourite = false

    @Inject lateinit var requestManager: RequestManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = (activity as MainActivity).mainViewModel
    }


    override fun layoutId(): Int {
        return R.layout.fragment_product_details
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        mViewModel.observeProduct().observe(viewLifecycleOwner, Observer {
            product = it
            isProductFavourite = it.isFavourite!!
            drawProduct()
        })

        manageFavouriteState()

        handleBackPress()

        handleAddToCart()

    }

    private fun handleAddToCart() {
        add_to_cart.setOnClickListener {
            Toast.makeText(context,"Product has been added to cart",Toast.LENGTH_LONG).show()
        }
    }

    private fun handleBackPress() {
        details_back_arrow.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun drawProduct(){
        details_is_new.visibility = if (product.isNew!!) View.VISIBLE else View.INVISIBLE
        details_title.text = product.title
        details_price.text = product.price
        details_to_favourite.setImageResource(if (isProductFavourite) R.drawable.ic_filled_favorite else R.drawable.ic_favorite)
        requestManager
            .load(product.image)
            .placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(details_image)
    }

    private fun manageFavouriteState(){
        favouriteViewState()
        onFavouriteClickListener()
    }

    private fun favouriteViewState(){
        when(isProductFavourite){
            true -> details_to_favourite.setImageResource(R.drawable.ic_filled_favorite)
            false -> details_to_favourite.setImageResource(R.drawable.ic_favorite)
        }
    }

    private fun onFavouriteClickListener(){
        details_to_favourite.setOnClickListener {
            when(isProductFavourite){
                true -> animateFavourite {
                    details_to_favourite.setImageResource(R.drawable.ic_favorite)
                    removeFromFavourite(product)
                    isProductFavourite = false
                }
                false -> animateFavourite {
                    details_to_favourite.setImageResource(R.drawable.ic_filled_favorite)
                    addToFavourite(product)
                    isProductFavourite = true
                }
            }
        }
    }

    private fun addToFavourite(product: Product){
        mViewModel.addToFavourites(productMapper.mapFrom(product))
    }

    private fun removeFromFavourite(product: Product){
        mViewModel.removeFromFavourites(productMapper.mapFrom(product))
    }

    private fun animateFavourite(favAction: () -> Unit){
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,0f,1f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,0f,1f)
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA,0f,1f)

        CoroutineScope(Dispatchers.Main).launch {

            ObjectAnimator.ofPropertyValuesHolder(details_to_favourite,scaleX,scaleY,alpha).apply {
                interpolator = OvershootInterpolator()
                doOnEnd { favAction() }
            }.setDuration(700L).start()
        }

    }


}