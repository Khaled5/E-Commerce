package com.easyinc.e_commerce.remote

import com.easyinc.e_commerce.data.model.ProductEntity
import com.easyinc.e_commerce.data.repositoty.CatalogRemote
import com.easyinc.e_commerce.remote.mapper.ProductModelMapper
import com.easyinc.e_commerce.remote.service.CatalogApi
import javax.inject.Inject

class CatalogRemoteImpl
@Inject constructor(
    private val catalogApi: CatalogApi,
    private val productModelMapper: ProductModelMapper
): CatalogRemote {

    override suspend fun fetchCatalog(): List<ProductEntity> {
        return catalogApi.fetchCatalogAsync().await().map {
            productModelMapper.mapFrom(it)
        }
    }

}