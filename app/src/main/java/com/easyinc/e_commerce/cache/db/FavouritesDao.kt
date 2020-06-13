package com.easyinc.e_commerce.cache.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.easyinc.e_commerce.cache.model.CachedProduct

@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(productEntity: CachedProduct)

    @Delete
    suspend fun deleteFavourite(productEntity: CachedProduct)

    @Query(Constants.GET_FAV_QUERY)
    fun getFavourites(): List<CachedProduct>

}