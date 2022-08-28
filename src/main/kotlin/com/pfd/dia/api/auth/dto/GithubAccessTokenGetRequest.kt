package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pfd.dia.api.auth.constant.AuthJsonObject

data class GithubAccessTokenGetRequest(
    val code: String,
    @JsonProperty(AuthJsonObject.clientId)
    val clientId: String,
    @JsonProperty(AuthJsonObject.clientSecret)
    val clientSecret: String,
)
