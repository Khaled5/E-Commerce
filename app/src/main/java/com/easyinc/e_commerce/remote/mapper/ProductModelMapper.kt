package com.easyinc.e_commerce.remote.mapper

import com.easyinc.e_commerce.data.model.ProductEntity
import com.easyinc.e_commerce.remote.model.ProductModel
import javax.inject.Inject

class ProductModelMapper @Inject constructor(): ModelMapper<ProductModel,ProductEntity> {
    override fun mapFrom(map: ProductModel): ProductEntity {
        return ProductEntity(
            map.id,
            map.image,
            map.new,
            map.price,
            map.title
        )
    }
}