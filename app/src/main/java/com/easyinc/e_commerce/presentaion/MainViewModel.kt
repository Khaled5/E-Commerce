package com.easyinc.e_commerce.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.easyinc.e_commerce.common.base.viewmodel.BaseViewModel
import com.easyinc.e_commerce.common.util.Resource
import com.easyinc.e_commerce.domain.usecase.*
import com.easyinc.e_commerce.presentaion.functionality.SingleLiveEvent
import com.easyinc.e_commerce.presentaion.mapper.ProductViewMapper
import com.easyinc.e_commerce.presentaion.model.ProductView
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val fetchCatalogUseCase: FetchCatalogUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val updateCatalogWithFavouriteUseCase: UpdateCatalogWithFavouriteUseCase,
    private val productViewMapper: ProductViewMapper
): BaseViewModel() {

    private val catalog  by lazy{ MutableLiveData<List<ProductView>>()}
    private val favourites by lazy { MutableLiveData<List<ProductView>>() }
    private val message by lazy { SingleLiveEvent<Resource<Pair<String, Int>>>() }


    private var catalogList = listOf<ProductView>()

    init {
        getCatalog()
    }

    fun observeCatalog(): LiveData<List<ProductView>> = catalog
    fun observeFavourites(): LiveData<List<ProductView>> = favourites
    fun observeMessage(): LiveData<Resource<Pair<String,Int>>> = message

    fun getFavourites(){
        getFavouritesUseCase.execute {
            onComplete {
                favourites.value  = it.map {product ->
                productViewMapper.mapTo(product)
            }
            }

            onError { message.value = Resource.Error(it.message.toString()) }

            onCancel {  }
        }
    }

    fun getCatalog(){
        message.value = Resource.Loading(null)
        fetchCatalogUseCase.execute {
            onComplete {
                val list = it.map {product ->
                    productViewMapper.mapTo(product)}
                catalogList = list

                catalog.value  = it.map {product ->
                productViewMapper.mapTo(product)

            }
            }

            onError { message.value = Resource.Error(it.message.toString()) }

            onCancel { }
        }
    }

    fun addToFavourites(product: ProductView, messageToSnackbarCode: Int = 10){
        addFavouriteUseCase.execute(
            productViewMapper.mapFrom(product)
        ) {
            onComplete {
                updateFavourites()
                message.value = Resource.Success(Pair(ADD_FAVOURITE_SUCCESS_MESSAGE,messageToSnackbarCode))
            }

            onError { message.value = Resource.Error(it.message!!,Pair(it.message,it.code!!)) }

            onCancel { }
        }
    }

    fun removeFromFavourites(product: ProductView, messageToSnackbarCode: Int = 10){
        removeFavouriteUseCase.execute(
            productViewMapper.mapFrom(product)
        ) {
            onComplete {
                updateFavourites()
                getFavourites()
                message.value = Resource.Success(Pair(REMOVE_FAVOURITE_SUCCESS_MESSAGE,messageToSnackbarCode))
            }

            onError {
                message.value = Resource.Error(it.message!!,Pair(it.message,it.code!!)) }

            onCancel {  }
        }
    }

    private fun updateFavourites(){
        updateCatalogWithFavouriteUseCase.execute(catalogList.map {
            productViewMapper.mapFrom(it)
        }) {
            onComplete {
                catalog.value = it.map {product ->
                    productViewMapper.mapTo(product)
                }
            }
        }
    }



    // Clear
    override fun onCleared() {
        super.onCleared()
        fetchCatalogUseCase.unsubscribe()
        addFavouriteUseCase.unsubscribe()
        getFavouritesUseCase.unsubscribe()
        removeFavouriteUseCase.unsubscribe()
    }


}