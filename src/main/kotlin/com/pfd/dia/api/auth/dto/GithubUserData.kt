package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubUserData(
    val login: String,
    val id: Long,
    @JsonProperty("node_id")
    val nodeId: String,
    @JsonProperty("avatar_url")
    val avatarUrl: String,
    @JsonProperty("html_url")
    val htmlUrl: String,
    val company: String?,
)
