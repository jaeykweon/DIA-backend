package com.pfd.dia.api.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import com.pfd.dia.api.DiaAuthenticateFailException
import com.pfd.dia.api.auth.constant.AuthProperty
import com.pfd.dia.api.auth.constant.TokenType
import com.pfd.dia.api.auth.constant.TokenType.ACCESS_TOKEN
import com.pfd.dia.api.auth.constant.TokenType.REFRESH_TOKEN
import org.springframework.stereotype.Component
import java.util.Date
import kotlin.jvm.Throws

@Component
class AuthJwtUtil(
    private val authProperty: AuthProperty
) {
    private val accessAlgorithm: Algorithm by lazy { Algorithm.HMAC256(authProperty.ACCESS_SECRET) }
    private val refreshAlgorithm: Algorithm by lazy { Algorithm.HMAC256(authProperty.REFRESH_SECRET) }


    fun generateAccessToken(userId:Long, oauthId: String, profileImageUrl: String): String =
        generateBasicToken()
            .withExpiresAt(Date(Date().time + authProperty.ACCESS_EXPIRE_TIME))
            .withClaim(JwtClaims.USER_ID, userId)
            .withClaim(JwtClaims.OAUTH_ID, oauthId)
            .withClaim(JwtClaims.PROFILE_IMAGE_URL, profileImageUrl)
            .sign(accessAlgorithm)

    fun generateRefreshToken(userId:Long, oauthId: String): String =
        generateBasicToken()
            .withExpiresAt(Date(Date().time + authProperty.REFRESH_EXPIRE_TIME))
            .withClaim(JwtClaims.USER_ID, userId)
            .withClaim(JwtClaims.OAUTH_ID, oauthId)
            .sign(refreshAlgorithm)

    private fun generateBasicToken() = JWT.create()
        .withIssuer(authProperty.JWT_ISSUER)
        .withSubject(authProperty.JWT_SUBJECT)
        .withIssuedAt(Date())

    @Throws(TokenExpiredException::class)
    fun verify(token: String, type: TokenType): DecodedJWT {
        val algorithm = when (type) {
            ACCESS_TOKEN -> accessAlgorithm
            REFRESH_TOKEN -> refreshAlgorithm
        }
        return JWT.require(algorithm)
            .withIssuer(authProperty.JWT_ISSUER)
            .withSubject(authProperty.JWT_SUBJECT)
            .build()
            .verify(token)
//            .apply { // verify ????????? ???????????? expireTime ????????????.
//                if(expiresAt < Date()) { throw DiaTokenExpiredException("?????? ????????? ?????????????????????") }
//            }
    }

    fun extractUserId(jwt: DecodedJWT): Long =
        jwt.getClaim(JwtClaims.USER_ID)?.asLong()
            ?: throw DiaAuthenticateFailException("???????????? ?????? ?????? user_id ?????????.")

    fun extractOauthId(jwt: DecodedJWT): String =
        jwt.getClaim(JwtClaims.OAUTH_ID)?.asString()
            ?: throw DiaAuthenticateFailException("???????????? ?????? ?????? oauth_id ?????????.")

    fun extractProfileImageUrl(jwt: DecodedJWT): String =
        jwt.getClaim(JwtClaims.PROFILE_IMAGE_URL)?.asString()
            ?: throw DiaAuthenticateFailException("???????????? ?????? profile image url ?????????.")

    private object JwtClaims {
        const val USER_ID = "user_id"
        const val OAUTH_ID = "oauth_id"
        const val PROFILE_IMAGE_URL = "profile_image_url"
    }
}