package com.easyinc.e_commerce.data.mapper

import com.easyinc.e_commerce.data.model.ProductEntity
import com.easyinc.e_commerce.domain.model.Product
import javax.inject.Inject

class ProductEntityMapper @Inject constructor(): EntityMapper<ProductEntity,Product> {
    override fun mapFrom(entity: ProductEntity): Product {
        return Product(
            entity.id,
            entity.image,
            entity.new,
            entity.price,
            entity.title
        )
    }

    override fun mapTo(entity: Product): ProductEntity {
        return ProductEntity(
            entity.id,
            entity.image,
            entity.new,
            entity.price,
            entity.title
        )
    }
}