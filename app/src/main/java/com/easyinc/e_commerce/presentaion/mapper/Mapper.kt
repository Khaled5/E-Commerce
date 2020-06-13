package com.easyinc.e_commerce.presentaion.mapper

interface Mapper<V, D> {

    fun mapTo(type: D): V

    fun mapFrom(type: V): D
}