package com.easyinc.e_commerce.remote.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("image")
    @Expose
    val image: String? = null,
    @SerializedName("new")
    @Expose
    val new: Boolean? = false,
    @SerializedName("price")
    @Expose
    val price: Double? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null
)