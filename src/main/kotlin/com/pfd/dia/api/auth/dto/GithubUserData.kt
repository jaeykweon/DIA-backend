package com.pfd.dia.api.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pfd.dia.api.auth.constant.AuthJsonObject

data class GithubUserData(
    val login: String,
    val id: Long,
    @JsonProperty(AuthJsonObject.nodeId)
    val nodeId: String,
    @JsonProperty(AuthJsonObject.avatarUrl)
    val avatarUrl: String,
    @JsonProperty(AuthJsonObject.htmlUrl)
    val htmlUrl: String,
    val company: String?,
)
