package com.easyinc.e_commerce.data.repositoty

import com.easyinc.e_commerce.data.model.ProductEntity

interface CatalogRemote {

    suspend fun fetchCatalog(): List<ProductEntity>

}