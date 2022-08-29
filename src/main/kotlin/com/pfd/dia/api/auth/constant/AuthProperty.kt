package com.pfd.dia.api.auth.constant

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class AuthProperty {
    @Value("\${jwt.issuer}")
    val JWT_ISSUER: String = "pfd"

    @Value("\${jwt.subject}")
    val JWT_SUBJECT: String = "dia"

    @Value("\${jwt.access_secret}")
    val ACCESS_SECRET: String = "ACCESS_SECRET"

    @Value("\${jwt.refresh_secret}")
    val REFRESH_SECRET: String = "REFRESH_SECRET"

    @Value("\${jwt.access_time}")
    val ACCESS_EXPIRE_TIME: Long = 60L * 60 * 2 * 1000

    @Value("\${jwt.refresh_time}")
    val REFRESH_EXPIRE_TIME: Long = 60L * 60 * 24 * 30 * 1000

    @Value("\${oauth.github.access_url}")
    val GITHUB_ACCESS_TOKEN_URL: String = "https://github.com/login/oauth/access_token"

    @Value("\${oauth.github.user_data_url}")
    val GITHUB_USER_DATA_URL: String = "https://api.github.com/user"

    @Value("\${oauth.github.client_id}")
    val GITHUB_CLIENT_ID: String = ""

    @Value("\${oauth.github.client_secret}")
    val GITHUB_CLIENT_SECRET: String = ""
}