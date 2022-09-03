package com.pfd.dia.api

import org.springframework.http.HttpStatus.Series

class DiaUnauthenticatedException(message: String): RuntimeException(message)

class DiaUnauthorizedException(message: String): RuntimeException(message)

class DiaNotFoundException(message: String): RuntimeException(message)

class DiaTokenExpiredException(message: String): RuntimeException(message)

enum class CustomHttpStatus(val status: Int, val series: Series, val reasonPhrase: String) {
    TOO_LATE(427, Series.CLIENT_ERROR,"too late")
    ;
}