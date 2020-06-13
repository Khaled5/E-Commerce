package com.easyinc.e_commerce.di.module

import com.easyinc.e_commerce.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}