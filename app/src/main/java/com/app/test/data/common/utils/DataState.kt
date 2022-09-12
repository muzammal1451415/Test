package com.app.test.data.common.utils

import com.app.test.domain.base.ErrorResponsee


sealed class DataState<out T> {
    data class Success<out T>(val value: T) : DataState<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponsee? = null) : DataState<Nothing>()
//    object Loading : DataState<Nothing>()
}