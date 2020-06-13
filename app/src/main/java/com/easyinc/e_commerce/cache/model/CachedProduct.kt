package com.easyinc.e_commerce.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easyinc.e_commerce.cache.db.Constants.FAV_TABLE

@Entity(tableName = FAV_TABLE)
data class CachedProduct(
    @PrimaryKey
    val id: Int?,
    val image: String?,
    val isNew: Boolean?,
    val price: Double?,
    val title: String?
)