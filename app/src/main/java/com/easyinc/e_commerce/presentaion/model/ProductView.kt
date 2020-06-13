package com.easyinc.e_commerce.presentaion.model

data class ProductView(
    val id: Int?,
    val image: String?,
    val new: Boolean?,
    val price: Double?,
    val title: String?,
    var isFavourite: Boolean?
)