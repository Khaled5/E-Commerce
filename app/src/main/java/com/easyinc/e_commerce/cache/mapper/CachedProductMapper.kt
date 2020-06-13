package com.easyinc.e_commerce.cache.mapper

import com.easyinc.e_commerce.cache.model.CachedProduct
import com.easyinc.e_commerce.data.model.ProductEntity
import javax.inject.Inject

class CachedProductMapper @Inject constructor(): CacheMapper<CachedProduct,ProductEntity> {
    override fun mapFromCache(cache: CachedProduct): ProductEntity {
        return ProductEntity(
            cache.id,
            cache.image,
            cache.isNew,
            cache.price,
            cache.title
        )
    }

    override fun mapToCache(entity: ProductEntity): CachedProduct {
        return CachedProduct(
            entity.id,
            entity.image,
            entity.new,
            entity.price,
            entity.title
        )
    }
}