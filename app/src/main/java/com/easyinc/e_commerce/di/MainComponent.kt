package com.easyinc.e_commerce.di

import android.app.Application
import com.easyinc.e_commerce.EcommerceApp
import com.easyinc.e_commerce.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    CacheModule::class,
    RemoteModule::class,
    RepositoryModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class]
)
interface MainComponent : AndroidInjector<EcommerceApp> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent

    }

}