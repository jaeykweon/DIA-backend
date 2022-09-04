package com.pfd.dia.api

import org.springframework.http.HttpStatus.Series

class DiaAuthenticateFailException(message: String): RuntimeException(message)

class DiaAuthorizeFailException(message: String): RuntimeException(message)

class DiaNotFoundException(message: String): RuntimeException(message)

enum class CustomHttpStatus(val status: Int, val series: Series, val reasonPhrase: String) {
    TOO_LATE(427, Series.CLIENT_ERROR,"too late")
    ;
}