package com.idd.dia.domain

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val data: T? = null,
    val message: String? = null,
) {
    companion object {

        @JvmStatic
        fun <T> success(data: T? = null, message: String? = null): ApiResponse<T> =
            ApiResponse(isSuccess = true, data = data, message = message)

        @JvmStatic
        fun error(message: String?) =
            ApiResponse<Nothing>(
                isSuccess = false,
                message = message
            )
    }
}