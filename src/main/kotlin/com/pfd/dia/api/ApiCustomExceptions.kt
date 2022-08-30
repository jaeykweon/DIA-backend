package com.pfd.dia.api

class DiaUnauthenticatedException(message: String): RuntimeException(message)

class DiaUnauthorizedException(message: String): RuntimeException(message)

class DiaNotFoundException(message: String): RuntimeException(message)