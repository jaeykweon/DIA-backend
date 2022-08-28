package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pfd.dia.api.auth.constant.AuthJsonObject

data class LogInResponse(
    @JsonProperty(AuthJsonObject.accessToken)
    val accessToken: String,
    @JsonProperty(AuthJsonObject.refreshToken)
    val refreshToken: String
)
