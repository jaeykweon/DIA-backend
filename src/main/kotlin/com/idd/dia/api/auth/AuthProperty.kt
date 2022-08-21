package com.idd.dia.api.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class AuthProperty (
    @Value("\${oauth.github.client_id}")
    val GITHUB_CLIENT_ID: String,

    @Value("\${oauth.github.client_secret}")
    val GITHUB_CLIENT_SECRET: String
)