package com.pfd.dia.api

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(DiaNotFoundException::class)
    fun handleNotFoundException(e: DiaNotFoundException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(DiaAuthenticateFailException::class)
    fun handleUnauthenticatedException(e: DiaAuthenticateFailException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(DiaAuthorizeFailException::class)
    fun handleForbiddenException(e: DiaAuthorizeFailException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(e.message))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ApiResponse<Nothing>> {
        logging(e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error("요청에 필요한 정보가 부족합니다(HttpMessageNotReadableException)"))
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
