package com.easyinc.e_commerce.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easyinc.e_commerce.cache.model.CachedProduct

@Database(
    entities = [CachedProduct::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

}