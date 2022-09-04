package com.pfd.dia.api.auth

import com.auth0.jwt.interfaces.DecodedJWT
import com.pfd.dia.api.DiaAuthenticateFailException
import org.springframework.stereotype.Component

@Component
class UserContextHolder(
    private val authJwtUtil: AuthJwtUtil,
) {
    private val userHolder = ThreadLocal.withInitial { UserHolder() }

    val id: Long
        get() = userHolder.get().id ?: throw DiaAuthenticateFailException("유저 정보가 없습니다.")

    fun set(jwt: DecodedJWT) {
        this.userHolder.get().apply {
            this.id = authJwtUtil.extractUserId(jwt)
            this.oauthId = authJwtUtil.extractOauthId(jwt)
        }
    }

    fun clear() = userHolder.remove()

    private class UserHolder {
        var id: Long? = null
        var oauthId: String? = null
    }
}