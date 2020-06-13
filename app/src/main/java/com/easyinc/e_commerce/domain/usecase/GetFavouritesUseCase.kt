package com.easyinc.e_commerce.domain.usecase

import androidx.lifecycle.LiveData
import com.easyinc.e_commerce.domain.model.Product
import com.easyinc.e_commerce.domain.repository.ICatalogRepository
import com.easyinc.e_commerce.domain.usecase.base.UseCase
import com.easyinc.e_commerce.domain.util.CloudErrorMapper
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val catalogRepository: ICatalogRepository
) : UseCase<List<Product>, Unit>(errorUtil){

    override suspend fun executeOnBackground(params: Unit?): List<Product> {
        return catalogRepository.getFavourites()
    }
}