package com.easyinc.e_commerce.common.base

import android.view.View
import com.easyinc.e_commerce.common.base.viewmodel.factory.getViewModel
import com.easyinc.e_commerce.common.extentions.androidLazy
import com.easyinc.e_commerce.common.extentions.errorSnack
import com.easyinc.e_commerce.common.extentions.snack
import com.easyinc.e_commerce.common.network_state.Event
import com.easyinc.e_commerce.common.network_state.NetworkConnectivityListener
import com.easyinc.e_commerce.presentaion.MainViewModel
import com.easyinc.tasking.common.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

open class BaseActivity: DaggerAppCompatActivity(), NetworkConnectivityListener {

    private var snackBar: Snackbar? = null
    private var isNetworkConnected: Boolean = true


    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    val mainViewModel by androidLazy {
        getViewModel<MainViewModel>(viewModelFactory)
    }

    fun showRegularSnackbar(message: String, sView: View = nav_host_fragment){
        sView.snack(message,NORMAL_DURATION)
    }

    fun showErrorSnackBar(message: String, duration: Int = NORMAL_DURATION) {
        nav_host_fragment.errorSnack(message,duration){
            snackBar = this
        }
    }

    override fun networkConnectivityChanged(event: Event) {
        when(event.state.isConnected){
            true -> {
                snackBar?.dismiss()
                hideAll()

                //To avoid recalling getCatalog method, when network is not stable
                if (isNetworkConnected)
                    mainViewModel.getCatalog()

                isNetworkConnected = false
            }
            false -> {
                isNetworkConnected = true
                showErrorSnackBar("No Internet Connection!", INDEFINITE_DURATION)
            }
        }
    }

    fun showLoading(){
        frame_loading.visibility = View.VISIBLE
        frame_internet_error.visibility = View.GONE
    }


    fun showNoConnection(){
        frame_loading.visibility = View.GONE
        frame_internet_error.visibility = View.VISIBLE
    }

    fun hideAll(){
        frame_loading.visibility = View.GONE
        frame_internet_error.visibility = View.GONE
    }

    companion object{
        const val NORMAL_DURATION = 3000
        const val INDEFINITE_DURATION = Snackbar.LENGTH_INDEFINITE
    }

}