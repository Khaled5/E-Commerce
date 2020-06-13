package com.easyinc.e_commerce.data

import com.easyinc.e_commerce.common.Logger
import com.easyinc.e_commerce.data.mapper.ProductEntityMapper
import com.easyinc.e_commerce.data.store.CatalogCacheDataStore
import com.easyinc.e_commerce.data.store.CatalogRemoteDataStore
import com.easyinc.e_commerce.domain.model.Product
import com.easyinc.e_commerce.domain.repository.ICatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl
@Inject constructor(
    private val catalogCacheDataStore: CatalogCacheDataStore,
    private val catalogRemoteDataStore: CatalogRemoteDataStore,
    private val productEntityMapper: ProductEntityMapper
): ICatalogRepository {

    private var catalogList = listOf<Product>()
    private var favList = listOf<Product>()

    override suspend fun addFavourite(product: Product) {
        return catalogCacheDataStore.addFavourite(
            productEntityMapper.mapTo(product)
        )
    }

    override suspend fun deleteFavourite(product: Product) {
        return catalogCacheDataStore.deleteFavourite(
            productEntityMapper.mapTo(product)
        )
    }

    override suspend fun getFavourites(): List<Product> {
        return catalogCacheDataStore.getFavourites().map {
            productEntityMapper.mapFrom(it)
        }
    }

    override suspend fun updateCatalog(catalog: List<Product>): List<Product>{
        favList = getFavourites()

        if (favList.isNotEmpty()){
            catalog.map {catalogProduct ->
                val o = favList.find {it.id == catalogProduct.id}
                catalogProduct.isFavourite = o != null
            }
        }

        return catalog
    }

    override suspend fun fetchCatalog(): List<Product> {


        favList = getFavourites()

        catalogList = catalogRemoteDataStore.fetchCatalog().map {
            productEntityMapper.mapFrom(it)
        }
        if (favList.isNotEmpty()){
            catalogList.map {catalogProduct ->
                catalogProduct.isFavourite = favList.contains(catalogProduct)
            }
        }


        return catalogList
    }


}