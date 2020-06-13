package com.easyinc.e_commerce.di.module

import com.easyinc.e_commerce.ui.catalog.CatalogFragment
import com.easyinc.e_commerce.ui.favourite.FavouritesFragment
import com.easyinc.e_commerce.ui.product_deatils.ProductDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideProductDetailsFragment(): ProductDetailsFragment

    @ContributesAndroidInjector
    abstract fun provideFavouritesFragment(): FavouritesFragment

    @ContributesAndroidInjector
    abstract fun provideCatalogFragment(): CatalogFragment

}