package com.easyinc.e_commerce.common.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easyinc.e_commerce.common.util.Resource
import com.easyinc.e_commerce.domain.usecase.RemoveFavouriteUseCase
import com.easyinc.e_commerce.presentaion.mapper.ProductViewMapper
import com.easyinc.e_commerce.presentaion.model.ProductView
import com.easyinc.e_commerce.ui.model.Product

open class BaseViewModel constructor() : ViewModel() {



    val productDetails by lazy { MutableLiveData<Product>() }



    fun observeProduct(): LiveData<Product> = productDetails


    companion object{
        val ADD_FAVOURITE_SUCCESS_MESSAGE = "Товар добавлен в избранное"
        val REMOVE_FAVOURITE_SUCCESS_MESSAGE = "Товар удален из списка избранных"
    }
}