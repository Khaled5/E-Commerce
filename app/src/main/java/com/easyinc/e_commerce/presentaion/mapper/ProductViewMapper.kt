package com.easyinc.e_commerce.presentaion.mapper

import com.easyinc.e_commerce.domain.model.Product
import com.easyinc.e_commerce.presentaion.model.ProductView
import javax.inject.Inject

class ProductViewMapper @Inject constructor(): Mapper<ProductView, Product> {
    override fun mapTo(type: Product): ProductView {
        return ProductView(
            type.id,
            type.image,
            type.new,
            type.price,
            type.title,
            type.isFavourite
        )
    }

    override fun mapFrom(type: ProductView): Product {
        return Product(
            type.id,
            type.image,
            type.new,
            type.price,
            type.title,
            type.isFavourite
        )
    }
}