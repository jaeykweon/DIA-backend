package com.pfd.dia.api.auth.constant

import kotlin.jvm.Throws

enum class TokenType(val typeName:String) {
    ACCESS_TOKEN("ACCESS"),
    REFRESH_TOKEN("REFRESH");

    companion object {
        @Throws(IllegalArgumentException::class)
        @JvmStatic
        fun from(input: String): TokenType {
            return TokenType.valueOf(input.uppercase())
        }
    }
}