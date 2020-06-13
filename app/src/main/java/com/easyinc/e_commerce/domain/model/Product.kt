package com.easyinc.e_commerce.domain.model

data class Product(
    var id: Int?,
    val image: String?,
    var new: Boolean?,
    val price: Double?,
    val title: String?,
    var isFavourite: Boolean? = false
)