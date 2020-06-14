package com.easyinc.e_commerce.domain.util

import android.util.Log
import com.easyinc.e_commerce.common.Logger
import com.easyinc.e_commerce.domain.util.response.ErrorModel
import com.easyinc.e_commerce.domain.util.response.ErrorStatus
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CloudErrorMapper @Inject constructor(private val gson: Gson) {

    fun mapToDomainErrorException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    ErrorModel(ErrorStatus.UNAUTHORIZED)
                } else {
                    getHttpError(throwable.response()?.errorBody())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(TIME_OUT,1,ErrorStatus.TIMEOUT)
            }

            // handle connection error
            is IOException -> {
                ErrorModel(CHECK_INTERNET_CONNECTION,2,ErrorStatus.NO_CONNECTION)
            }

            is UnknownHostException -> {
                ErrorModel(UNABLE_TO_CONNECT_TO_SERVER,3,ErrorStatus.NO_CONNECTION)
            }
            else -> ErrorModel(SOMETHING_WENT_WRONG,4,ErrorStatus.NOT_DEFINED)
        }
        return errorModel!!
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            val result = body?.string()
            Logger.dt("getErrorMessage() called with: errorBody = [$result]")
            val json = Gson().fromJson(result, JsonObject::class.java)
            ErrorModel(  json.toString(), 400, ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.NOT_DEFINED)
        }

    }

    companion object{
        const val SOMETHING_WENT_WRONG = "Что-то пошло не так!"
        const val CHECK_INTERNET_CONNECTION = "Нет соединения с интернетом!"
        const val TIME_OUT = "Истекло время ожидания!"
        const val UNABLE_TO_CONNECT_TO_SERVER = "Не удалось связаться с сервером!"
    }
}