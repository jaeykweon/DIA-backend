package com.pfd.dia.api.auth

import com.pfd.dia.api.auth.constant.AuthJsonObject
import com.pfd.dia.api.auth.constant.AuthProperty
import com.pfd.dia.api.auth.dto.GithubAccessTokenGetRequest
import com.pfd.dia.api.auth.dto.GithubAccessTokenGetResponse
import com.pfd.dia.api.auth.dto.GithubUserData
import com.pfd.dia.api.auth.dto.LogInResponse
import com.pfd.dia.api.command.user.UserCommandService
import com.pfd.dia.api.command.user.UserReaderService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
@Transactional
class AuthService(
    private val authJwtUtil: AuthJwtUtil,
    private val authProperty: AuthProperty,
    private val authTokenRepository: AuthTokenRepository,
    private val userReaderService: UserReaderService,
    private val userCommandService: UserCommandService,
) {
    fun logInWithGithub(code:String): LogInResponse {

        val restTemplate = createRestTemplate()
        val header = createHeader()
        val githubAccessTokenGetRequest = GithubAccessTokenGetRequest(
            code = code,
            clientId = authProperty.GITHUB_CLIENT_ID,
            clientSecret = authProperty.GITHUB_CLIENT_SECRET
        )
        val githubAccessTokenResponse : ResponseEntity<GithubAccessTokenGetResponse> =
            restTemplate.postForEntity(
                authProperty.GITHUB_ACCESS_TOKEN_URL,
                HttpEntity(githubAccessTokenGetRequest, header),
                GithubAccessTokenGetResponse::class.java
            )

        header.set("Authorization", "Bearer "+ githubAccessTokenResponse.body!!.accessToken);
        val githubUserDataResponse = restTemplate.exchange(
            authProperty.GITHUB_USER_DATA_URL,
            HttpMethod.GET,
            HttpEntity<Map<String, Any>>(header),
            GithubUserData::class.java
        )
        val githubUserData = githubUserDataResponse.body!!
        val githubId = githubUserData.login
        val githubProfileImageUrl = githubUserData.avatarUrl

        val userId = userReaderService.findUserId(githubId, AuthJsonObject.github)
            ?: userCommandService.registerUser(
                githubId,
                AuthJsonObject.github,
                githubUserData.htmlUrl,
                githubProfileImageUrl
            )

        val accessToken = authJwtUtil.generateAccessToken(userId, githubId, githubProfileImageUrl)
        val refreshToken = authJwtUtil.generateRefreshToken(userId, githubId)
        val newAuthTokenEntity = AuthTokenEntity(userId= userId, accessToken = accessToken, refreshToken = refreshToken)
        val savedTokenEntity = authTokenRepository.save(newAuthTokenEntity)

        return LogInResponse(
            accessToken = savedTokenEntity.accessToken,
            refreshToken = savedTokenEntity.refreshToken
        )
    }

    private fun createRestTemplate(connectTimeOut: Int = 5000, readTimeOut: Int = 5000): RestTemplate {
        val httpRequestFactory = HttpComponentsClientHttpRequestFactory().apply {
            setConnectTimeout(connectTimeOut)
            setReadTimeout(readTimeOut)
        }
        return RestTemplate(httpRequestFactory)
    }

    private fun createHeader(
        contentType: MediaType = MediaType.APPLICATION_JSON,
        accept: List<MediaType> = listOf(MediaType.APPLICATION_JSON),
    ) = HttpHeaders().apply {
            this.contentType = contentType
            this.accept = accept
        }

}