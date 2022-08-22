package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubAccessTokenGetRequest(
    val code: String,
    @JsonProperty("client_id")
    val clientId: String,
    @JsonProperty("client_secret")
    val clientSecret: String,
)
