package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubAccessTokenGetResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    val scope: String,
)