package com.easyinc.e_commerce

import com.easyinc.e_commerce.common.network_state.NetworkStateHolder.registerConnectivityBroadcaster
import com.easyinc.e_commerce.di.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class EcommerceApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMainComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        registerConnectivityBroadcaster()
    }
}