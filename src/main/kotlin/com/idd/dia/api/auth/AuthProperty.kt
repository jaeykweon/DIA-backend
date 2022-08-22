package com.idd.dia.api.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class AuthProperty (

    @Value("\${jwt.issuer}")
    val JWT_ISSUER: String,

    @Value("\${jwt.subject}")
    val JWT_SUBJECT: String,

    @Value("\${jwt.access_secret}")
    val ACCESS_SECRET: String,

    @Value("\${jwt.refresh_secret}")
    val REFRESH_SECRET: String,

    @Value("\${oauth.github.client_id}")
    val GITHUB_CLIENT_ID: String,

    @Value("\${oauth.github.client_secret}")
    val GITHUB_CLIENT_SECRET: String,
)