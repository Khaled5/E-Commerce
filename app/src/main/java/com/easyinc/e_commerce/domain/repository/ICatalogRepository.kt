package com.easyinc.e_commerce.domain.repository

import com.easyinc.e_commerce.domain.model.Product

interface ICatalogRepository {

    suspend fun addFavourite(product: Product)

    suspend fun deleteFavourite(product: Product)

    suspend fun getFavourites(): List<Product>

    suspend fun fetchCatalog(): List<Product>

    suspend fun updateCatalog(catalog: List<Product>): List<Product>

}