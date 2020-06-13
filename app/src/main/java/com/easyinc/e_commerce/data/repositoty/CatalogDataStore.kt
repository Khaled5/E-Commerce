package com.easyinc.e_commerce.data.repositoty

import com.easyinc.e_commerce.data.model.ProductEntity

interface CatalogDataStore {

    suspend fun addFavourite(productEntity: ProductEntity)

    suspend fun deleteFavourite(productEntity: ProductEntity)

    suspend fun getFavourites(): List<ProductEntity>

    suspend fun fetchCatalog(): List<ProductEntity>

}