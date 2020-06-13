package com.easyinc.e_commerce.common.extentions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun <T> androidLazy(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snacks = Snackbar.make(this,message,length).also {
        it.setBackgroundTint(Color.BLACK)
        it.setTextColor(Color.WHITE)
        it.view.minimumHeight = 170

        (it.view as? Snackbar.SnackbarLayout)?.getChildAt(0)?.let { innerView ->
            innerView.minimumHeight = 170
        }
    }
    snacks.show()
}

fun View.errorSnack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val eSnack = Snackbar.make(this,message,length).also {
        it.setBackgroundTint(Color.RED)
        it.setTextColor(Color.WHITE)
        it.view.minimumHeight = 170

        (it.view as? Snackbar.SnackbarLayout)?.getChildAt(0)?.let { innerView ->
            innerView.minimumHeight = 170
        }
    }
    eSnack.f()
    eSnack.show()
}

fun Snackbar.action(action: String, color: Int = Color.WHITE, listener: (View) -> Unit) {
    setAction(action, listener)
    setActionTextColor(color)
}