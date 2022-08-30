package com.pfd.dia.api.auth.config

import com.pfd.dia.api.DiaUnauthorizedException
import com.pfd.dia.api.auth.AuthJwtUtil
import com.pfd.dia.api.auth.UserContextHolder
import com.pfd.dia.api.auth.constant.TokenType
import com.pfd.dia.api.auth.constant.TokenType.ACCESS_TOKEN
import com.pfd.dia.api.auth.constant.TokenType.REFRESH_TOKEN
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.IllegalStateException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenValidateInterceptor(
    private val jwtUtil: AuthJwtUtil,
    private val userContextHolder: UserContextHolder
): HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val requestMethod = request.method
        val requestServletPath = request.servletPath
        val authHeader = request.getHeader(AUTHORIZATION)

        if (authHeader.isNullOrBlank()) {
            val pair = requestMethod to requestServletPath
            if (DEFAULT_ALLOWED_API_URLS.contains(pair).not()) {
                logger.error("token이 필요한 api입니다.")
                response.sendError(401)
                return false
            }
            return true
        } else {
            val grantType = request.getParameter(GRANT_TYPE) ?: ACCESS_TOKEN.typeName
            checkTokenTypeAndDestination(grantType, requestServletPath)

            val token = extractToken(authHeader)
            return handleToken(grantType, token, response)
        }
    }

    private fun checkTokenTypeAndDestination(type: String, destination: String) {
        if (type == GRANT_TYPE_REFRESH && destination != "/api/v1/refresh-token") {
            // check: Unauthorized 가 맞나?
            throw DiaUnauthorizedException("해당 토큰으로 접근할 수 없는 요청입니다.")
        }
    }

    private fun handleToken(
        grantType: String?,
        token: String,
        response: HttpServletResponse
    ) = try {
        val tokenType = grantType?.run { TokenType.from(grantType) }
        val jwtUtil = when (tokenType) {
            ACCESS_TOKEN -> jwtUtil.verify(token, ACCESS_TOKEN)
            REFRESH_TOKEN -> jwtUtil.verify(token, REFRESH_TOKEN)
            else -> throw IllegalStateException("올바르지 않은 인증 타입입니다.")
        }
        userContextHolder.set(jwtUtil)
        true
    } catch (e: Exception) {
        logger.error("토큰 검증 실패. token = $token", e)
        response.sendError(401)
        false
    }

    private fun extractToken(token: String) =
        token.replace(BEARER, "").trim()

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        userContextHolder.clear()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
        private const val GRANT_TYPE = "grant_type"
        private const val GRANT_TYPE_REFRESH = "refresh_token"

        private val DEFAULT_ALLOWED_API_URLS = listOf(
            "POST" to "/api/v1/auth/**",
            "GET" to "/api/v1/auth/home",
            "GET" to "/api/v1/auth/github/oauth/callback",
        )
    }
}