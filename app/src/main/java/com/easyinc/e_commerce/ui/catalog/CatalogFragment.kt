package com.easyinc.e_commerce.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.easyinc.e_commerce.MainActivity
import com.easyinc.e_commerce.R
import com.easyinc.e_commerce.common.base.BaseFragment
import com.easyinc.e_commerce.ui.catalog.adapter.CatalogAdapter
import com.easyinc.e_commerce.ui.model.Product
import kotlinx.android.synthetic.main.catalog_toolbar.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import javax.inject.Inject

class CatalogFragment : BaseFragment() {

    @Inject
    lateinit var adapter: CatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = (activity as MainActivity).mainViewModel
    }

    override fun layoutId(): Int {
        return R.layout.fragment_catalog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initCatalogRecycler()

        initAdapterItemClickListener()

        observeCatalog()

        moveToFavourites()

    }

    private fun initCatalogRecycler(){
        catalog_recycler.adapter = adapter
        catalog_recycler.layoutManager = GridLayoutManager(context,2)
        catalog_recycler.itemAnimator = null
        catalog_recycler.setHasFixedSize(true)
    }

    private fun moveToFavourites(){
        add_product_tool_bar.setOnClickListener {
            navController.navigate(R.id.action_catalogFragment_to_favouritesFragment)
        }
    }

    private fun initAdapterItemClickListener(){
        adapter.clickListener = {product ->
            mViewModel.productDetails.postValue(product)
            navController.navigate(R.id.action_catalogFragment_to_productDetailsFragment)
        }

        adapter.favouriteClickListener = {product ->
            when(product.isFavourite){
                true -> removeFromFavourite(product)
                false -> addToFavourite(product)
            }
        }
    }

    private fun addToFavourite(product: Product){
        mViewModel.addToFavourites(
            productMapper.mapFrom(product)
        )
    }

    private fun removeFromFavourite(product: Product){
        mViewModel.removeFromFavourites(
            productMapper.mapFrom(product)
        )
    }

    private fun observeCatalog(){
        mViewModel.observeCatalog().observe(viewLifecycleOwner, Observer {
            catalog_empty_list.visibility = View.GONE

            if (it.isEmpty())
                catalog_empty_list.visibility = View.VISIBLE

            products_number.text = String.format(resources.getString(R.string.found_products), it.size)

            (activity as MainActivity).hideAll()
            adapter.submitList(it.map { o -> productMapper.mapTo(o) })
        }
        )
    }
}