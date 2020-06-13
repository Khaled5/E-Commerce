package com.easyinc.e_commerce.common

import android.util.Log

object Logger {

    private const val TAG = "E-Commerce"

    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}