package com.easyinc.e_commerce.data.store

import com.easyinc.e_commerce.data.model.ProductEntity
import com.easyinc.e_commerce.data.repositoty.CatalogDataStore
import com.easyinc.e_commerce.data.repositoty.CatalogRemote
import javax.inject.Inject

class CatalogRemoteDataStore
@Inject constructor(
    private val catalogRemote: CatalogRemote
): CatalogDataStore{

    override suspend fun addFavourite(productEntity: ProductEntity) {
        throw UnsupportedOperationException("Add favourite isn't supported here...")
    }

    override suspend fun deleteFavourite(productEntity: ProductEntity) {
        throw UnsupportedOperationException("Delete favourite isn't supported here...")
    }

    override suspend fun getFavourites(): List<ProductEntity> {
        throw UnsupportedOperationException("get favourites isn't supported here...")
    }

    override suspend fun fetchCatalog(): List<ProductEntity> {
        return catalogRemote.fetchCatalog()
    }
}