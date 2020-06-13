package com.easyinc.e_commerce.remote.service

import com.easyinc.e_commerce.remote.model.ProductsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CatalogApi {

    @GET("jeans-default.json")
    fun fetchCatalogAsync(): Deferred<ProductsResponse>

}