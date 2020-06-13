package com.easyinc.e_commerce.ui.mapper

interface ViewMapper<P, V> {

    fun mapTo(type: P) : V

    fun mapFrom(type: V): P
}