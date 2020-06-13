package com.easyinc.e_commerce.di.module

import android.app.Application
import androidx.room.Room
import com.easyinc.e_commerce.cache.db.AppDatabase
import com.easyinc.e_commerce.cache.db.Constants
import com.easyinc.e_commerce.cache.db.FavouritesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase{
        return Room
            .databaseBuilder(application,AppDatabase::class.java, Constants.DB_NAME)
            .build()
    }

    @Provides
    fun provideFavouriteDao(appDatabase: AppDatabase): FavouritesDao{
        return appDatabase.favouritesDao()
    }

}