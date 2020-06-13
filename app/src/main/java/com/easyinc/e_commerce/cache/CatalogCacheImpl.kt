package com.easyinc.e_commerce.cache

import com.easyinc.e_commerce.cache.db.FavouritesDao
import com.easyinc.e_commerce.cache.mapper.CachedProductMapper
import com.easyinc.e_commerce.data.model.ProductEntity
import com.easyinc.e_commerce.data.repositoty.CatalogCache
import javax.inject.Inject

class CatalogCacheImpl
@Inject constructor(
    private val favouritesDao: FavouritesDao,
    private val cachedProductMapper: CachedProductMapper
): CatalogCache {

    override suspend fun getFavourites(): List<ProductEntity> {
        return favouritesDao.getFavourites().map {
            cachedProductMapper.mapFromCache(it)
        }
    }

    private fun play(productEntity: ProductEntity) = productEntity

    override suspend fun addFavourite(productEntity: ProductEntity) {
        return favouritesDao.addFavourite(
            cachedProductMapper.mapToCache(productEntity)
        )
    }

    override suspend fun deleteFavourite(productEntity: ProductEntity) {
        return favouritesDao.deleteFavourite(
            cachedProductMapper.mapToCache(productEntity)
        )
    }

}