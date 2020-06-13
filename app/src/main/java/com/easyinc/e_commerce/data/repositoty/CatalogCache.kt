package com.easyinc.e_commerce.data.repositoty

import com.easyinc.e_commerce.data.model.ProductEntity

interface CatalogCache {
    suspend fun getFavourites(): List<ProductEntity>

    suspend fun addFavourite(productEntity: ProductEntity)

    suspend fun deleteFavourite(productEntity: ProductEntity)
}