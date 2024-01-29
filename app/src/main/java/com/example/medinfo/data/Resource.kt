package com.example.medinfo.data

import android.content.res.Resources.NotFoundException
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.HttpException
import java.io.IOException

suspend inline fun <reified T> fetchResources(
    emitter: FlowCollector<Resource<T>>, operation: () -> T
) {
    try {
        emitter.emit(Resource.Loading())
        val result = operation()
        emitter.emit(Resource.Success(result))
    } catch (e: IOException) {
        emitter.emit(Resource.Error(ErrorType.NoInternetConnection))
    } catch (e: HttpException) {
        emitter.emit(Resource.Error(ErrorType.HTTP))
    } catch (e: NotFoundException) {
        emitter.emit(Resource.Error(ErrorType.NotFound))
    }
}

fun handleResponse(
    response: Resource<*>,
    onSuccess: () -> Unit = {},
    onLoading: () -> Unit = {},
    onError: () -> Unit = {}
) {
    when (response) {
        is Resource.Success -> onSuccess()
        is Resource.Loading -> onLoading()
        is Resource.Error -> onError()
    }
}

sealed class Resource<T>(val data: T? = null, val error: ErrorType? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorType, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

enum class ErrorType {
    NoInternetConnection, NotFound, HTTP
}