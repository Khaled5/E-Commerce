package com.easyinc.e_commerce.data.store

import com.easyinc.e_commerce.data.model.ProductEntity
import com.easyinc.e_commerce.data.repositoty.CatalogCache
import com.easyinc.e_commerce.data.repositoty.CatalogDataStore
import javax.inject.Inject

class CatalogCacheDataStore
@Inject constructor(
    private val catalogCache: CatalogCache
): CatalogDataStore {

    override suspend fun addFavourite(productEntity: ProductEntity) {
        return catalogCache.addFavourite(productEntity)
    }

    override suspend fun deleteFavourite(productEntity: ProductEntity) {
        return catalogCache.deleteFavourite(productEntity)
    }

    override suspend fun getFavourites(): List<ProductEntity> {
        return catalogCache.getFavourites()
    }

    override suspend fun fetchCatalog(): List<ProductEntity> {
        throw UnsupportedOperationException("Fetching Catalog isn't supported here...")
    }

}