package com.easyinc.e_commerce

import android.os.Bundle
import androidx.lifecycle.Observer
import com.easyinc.e_commerce.common.base.BaseActivity
import com.easyinc.e_commerce.common.network_state.NetworkStateHolder
import com.easyinc.e_commerce.common.util.Resource
import kotlinx.android.synthetic.main.fragment_product_details.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternetConnectionOnce()

    }

    private fun checkInternetConnectionOnce(){
        when(NetworkStateHolder.isConnected){
            true -> observeMessage()
            false -> showNoConnection()
        }
    }

    private fun observeMessage(){
        mainViewModel.observeMessage().observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.ERROR -> {
                    if (it.data!!.second == 2) showErrorSnackBar(it.data.first, INDEFINITE_DURATION) else showErrorSnackBar(it.data.first)
                }
                Resource.Status.SUCCESS -> {
                    if (it.data!!.second == 10) showRegularSnackbar(it.data.first) else showRegularSnackbar(it.data.first, product_details_snack)
                }
            }
        })
    }





}