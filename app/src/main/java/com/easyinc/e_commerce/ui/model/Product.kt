package com.easyinc.e_commerce.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int?,
    val image: String?,
    val price: String?,
    val title: String?,
    val isNew: Boolean?,
    var isFavourite: Boolean?
): Parcelable