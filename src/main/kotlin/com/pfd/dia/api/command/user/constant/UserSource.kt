package com.pfd.dia.api.command.user.constant

import kotlin.jvm.Throws

enum class UserSource(value: String) {
    GITHUB("github");

    companion object {
        @Throws(IllegalArgumentException::class)
        @JvmStatic
        fun from(input: String): UserSource {
            return valueOf(input.uppercase())
        }
    }
}