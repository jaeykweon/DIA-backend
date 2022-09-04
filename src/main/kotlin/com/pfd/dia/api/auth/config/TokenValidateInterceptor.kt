package com.pfd.dia.api.auth.config

import com.auth0.jwt.exceptions.TokenExpiredException
import com.pfd.dia.api.DiaAuthenticateFailException
import com.pfd.dia.api.auth.AuthJwtUtil
import com.pfd.dia.api.auth.AuthTokenRepository
import com.pfd.dia.api.auth.UserContextHolder
import com.pfd.dia.api.auth.constant.TokenType.ACCESS_TOKEN
import com.pfd.dia.global.util.isNotNull
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenValidateInterceptor(
    private val authTokenRepository: AuthTokenRepository,
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
        val authHeader: String? = request.getHeader(AUTHORIZATION)
        val authId: Long? = request.getHeader(AUTH_ID)?.toLong()

        if (authHeader.isNullOrBlank()) {
            val pair = requestMethod to requestServletPath
            if (DEFAULT_ALLOWED_API_URLS.contains(pair).not()) {
                logger.error("token이 필요한 api입니다.")
                response.sendError(401)
                return false
            }
            return true
        }

        return handleToken(authId, extractToken(authHeader), response)
    }


    private fun handleToken(
        authId: Long?,
        token: String,
        response: HttpServletResponse
    ) = try {
        check(authId.isNotNull()) { throw DiaAuthenticateFailException("토큰 id가 없습니다.") }

        val tokenInServer = authTokenRepository.findByIdOrNull(authId)?.accessToken
            ?: throw DiaAuthenticateFailException("서버에 없는 토큰입니다.")
        check(token == tokenInServer) { throw DiaAuthenticateFailException("서버 토큰 정보와 다릅니다.") }

        val jwt = jwtUtil.verify(token, ACCESS_TOKEN)
        userContextHolder.set(jwt)
        true
    } catch (e: TokenExpiredException) {
        logger.error("토큰 유효 시간 만료 token = $token",)
        response.sendError(427, e.message)
        false
    } catch (e: Exception) {
        logger.error("토큰 검증 실패. token = $token", e)
        response.sendError(401, e.message)
        false
    }

    private fun extractToken(token: String): String {
        val (front, back) = token.split(" ")
        if(front != BEARER) { throw DiaAuthenticateFailException("토큰 형식이 올바르지 않습니다.") }
        return back.trim()
    }

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
        private const val AUTH_ID = "Auth-Id"
        private const val BEARER = "Bearer"

        private val DEFAULT_ALLOWED_API_URLS = listOf(
            "POST" to "/api/v1/auth/**",
            "GET" to "/api/v1/auth/home",
            "GET" to "/api/v1/auth/github/oauth/callback",
        )
    }
}