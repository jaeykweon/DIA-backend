package com.pfd.dia.api

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleForbiddenException(e: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ApiResponse<Nothing> {
        logging(e)
        return ApiResponse.error(e.fieldErrors.joinToString { "${it.field} : ${it.defaultMessage}" })
    }

    @ExceptionHandler(Exception::class)
    fun handleUntitledException(e: Exception): ApiResponse<Nothing> {
        logging(e)
        return ApiResponse.error("정의하지 않은 오류가 발생했습니다.")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        @JvmStatic
        fun logging(e: Exception) = logger.error("API error", e)
    }
}
