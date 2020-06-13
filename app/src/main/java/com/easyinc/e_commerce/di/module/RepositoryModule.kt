package com.easyinc.e_commerce.di.module

import com.easyinc.e_commerce.cache.CatalogCacheImpl
import com.easyinc.e_commerce.data.CatalogRepositoryImpl
import com.easyinc.e_commerce.data.repositoty.CatalogCache
import com.easyinc.e_commerce.data.repositoty.CatalogRemote
import com.easyinc.e_commerce.domain.repository.ICatalogRepository
import com.easyinc.e_commerce.remote.CatalogRemoteImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {


    @Binds
    abstract fun provideCatalogCache(catalogCacheImpl: CatalogCacheImpl): CatalogCache

    @Binds
    abstract fun provideCatalogRemote(catalogRemoteImpl: CatalogRemoteImpl): CatalogRemote

    @Binds
    abstract fun provideCatalogRepository(catalogRepositoryImpl: CatalogRepositoryImpl): ICatalogRepository

}