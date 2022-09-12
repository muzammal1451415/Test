package com.app.test.data.common.utils


import com.app.test.data.common.response.ResponseStatus
import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T> (
    @SerializedName("response") var response: ResponseStatus,
    @SerializedName("data") var data : List<T> ?= null
)


data class WrappedResponse<T> (
    @SerializedName("response") var response: ResponseStatus,
    @SerializedName("data") var data: T? = null
)