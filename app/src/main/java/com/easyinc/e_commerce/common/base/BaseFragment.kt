package com.easyinc.e_commerce.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.easyinc.e_commerce.presentaion.MainViewModel
import com.easyinc.e_commerce.ui.mapper.ProductMapper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {

    lateinit var navController: NavController

    @Inject lateinit var productMapper: ProductMapper

    lateinit var mViewModel: MainViewModel
    
    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(layoutId(), container, false)
    }

    companion object{
        val SNACKBAR_WITH_CUSTOM_POS = 20
        val REGULAR_SNAKBAR_POS = 10
    }

}