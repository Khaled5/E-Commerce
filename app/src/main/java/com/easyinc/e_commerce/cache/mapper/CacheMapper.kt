package com.easyinc.e_commerce.cache.mapper

interface CacheMapper<C, E> {

    fun mapFromCache(cache: C): E

    fun mapToCache(entity: E): C

}