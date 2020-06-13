package com.easyinc.e_commerce.ui.mapper

import com.easyinc.e_commerce.presentaion.model.ProductView
import com.easyinc.e_commerce.ui.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(): ViewMapper<ProductView,Product> {
    override fun mapTo(type: ProductView): Product {
        return Product(
            type.id,
            type.image,
            type.price.toString(),
            type.title,
            type.new,
            type.isFavourite
        )
    }

    override fun mapFrom(type: Product): ProductView {
        return ProductView(
            type.id,
            type.image,
            type.isNew,
            type.price?.toDouble(),
            type.title,
            type.isFavourite
        )
    }
}