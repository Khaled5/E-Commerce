package com.easyinc.e_commerce.ui.favourite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyinc.e_commerce.MainActivity
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.common.base.BaseFragment
import com.easyinc.e_commerce.ui.favourite.adapter.FavouriteAdapter
import kotlinx.android.synthetic.main.favourites_toolbar.*
import kotlinx.android.synthetic.main.fragment_favourites.*
import javax.inject.Inject

class FavouritesFragment : BaseFragment() {

    @Inject
    lateinit var adapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = (activity as MainActivity).mainViewModel
        mViewModel.getFavourites()
    }


    override fun layoutId(): Int {
        return R.layout.fragment_favourites
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initFavouritesRecycler()

        observeFavouritesRemoving()

        removeFromFavourites()

        handleClickItemListener()

        handleBackPress()

        handleAddToCart()

    }

    private fun handleAddToCart() {
       adapter.cartClickListener = {
            Toast.makeText(context,"Product has been added to cart", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleBackPress() {
        favourite_backpress.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun handleClickItemListener() {
        adapter.clickListener = {
            it.isFavourite = true
            mViewModel.productDetails.postValue(it)
            navController.navigate(R.id.action_favouritesFragment_to_productDetailsFragment)
        }
    }

    private fun initFavouritesRecycler(){
        favourite_recycler.adapter = adapter
        favourite_recycler.layoutManager = LinearLayoutManager(context)
        favourite_recycler.itemAnimator = DefaultItemAnimator()
        favourite_recycler.setHasFixedSize(true)
    }

    private fun removeFromFavourites(){
        adapter.removeFavClickListener = {
            mViewModel.removeFromFavourites(
                productMapper.mapFrom(it)
            )
        }
    }

    private fun observeFavouritesRemoving(){
        mViewModel.observeFavourites().observe(viewLifecycleOwner, Observer {
               fav_empty_list.visibility = View.GONE

            if (it.isEmpty())
                fav_empty_list.visibility = View.VISIBLE

               (activity as MainActivity).hideAll()
               adapter.submitList(it.map { o -> productMapper.mapTo(o) })


        })
    }

}