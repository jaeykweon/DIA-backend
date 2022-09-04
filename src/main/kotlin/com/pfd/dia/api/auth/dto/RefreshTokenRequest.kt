package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pfd.dia.api.auth.constant.AuthJsonObject
import javax.validation.constraints.NotEmpty

data class RefreshTokenRequest(
    @field:NotEmpty(message = "${AuthJsonObject.refreshToken}이 없거나 비어있습니다")
    @JsonProperty(AuthJsonObject.refreshToken)
    val refreshToken: String
)
