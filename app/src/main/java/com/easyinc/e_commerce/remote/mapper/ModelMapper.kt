package com.easyinc.e_commerce.remote.mapper

interface ModelMapper<in M, out E> {

    fun mapFrom(map: M) : E
}