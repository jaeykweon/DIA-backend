package com.pfd.dia.api.auth

import com.pfd.dia.api.ApiResponse
import com.pfd.dia.api.auth.dto.GithubAccessTokenGetRequest
import com.pfd.dia.api.auth.dto.GithubAccessTokenGetResponse
import com.pfd.dia.api.auth.dto.GithubUserData
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate
import javax.validation.constraints.NotEmpty

@Controller
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authProperty: AuthProperty
) {

    @GetMapping("/home")
    fun home(): String {
        return "OauthPage"
    }

    @GetMapping("/github/oauth/callback")
    fun githubOauthCallBackTest(
        @RequestParam @NotEmpty(message = "인증 코드가 비어있습니다.") code: String,
        @RequestParam("previous_url") previousUrl: String?
    ): ResponseEntity<ApiResponse<GithubUserData>> {

        val factory = HttpComponentsClientHttpRequestFactory().apply {
            setConnectTimeout(5000)
            setReadTimeout(5000)
        }
        val restTemplate = RestTemplate(factory)
        val header = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            accept = listOf(MediaType.APPLICATION_JSON)
        }
        val githubAccessTokenGetRequest = GithubAccessTokenGetRequest(
            code = code,
            clientId = authProperty.GITHUB_CLIENT_ID,
            clientSecret = authProperty.GITHUB_CLIENT_SECRET
        )

        val getGithubAccessTokenUrl = "https://github.com/login/oauth/access_token"

        val githubAccessToken : ResponseEntity<GithubAccessTokenGetResponse> =
            restTemplate.exchange(
                getGithubAccessTokenUrl,
                HttpMethod.POST,
                HttpEntity(githubAccessTokenGetRequest, header),
                GithubAccessTokenGetResponse::class.java
            )

        val githubUserDataUrl = "https://api.github.com/user"
        header.set("Authorization", "Bearer "+ githubAccessToken.body!!.accessToken);
        val githubUserDataResponse = restTemplate.exchange(
            githubUserDataUrl,
            HttpMethod.GET,
            HttpEntity<Map<String, Any>>(header),
            GithubUserData::class.java
        )

        // to-do : github access token 으로 jwt 생성하여 반환 
        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponse.success(
                data = githubUserDataResponse.body,
                message = "깃허브로 로그인 되었습니다."
            )
        )
    }


}