package com.pfd.dia.api.auth

import com.pfd.dia.api.ApiResponse
import com.pfd.dia.api.auth.constant.AuthJsonObject
import com.pfd.dia.api.auth.dto.AuthTokenResponse
import com.pfd.dia.api.auth.dto.RefreshTokenRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@Controller
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val userContextHolder: UserContextHolder,
) {

    @GetMapping("/home")
    fun home(): String {
        return "OauthPage"
    }

    @GetMapping("/github/oauth/callback")
    fun githubOauthCallBackTest(
        @RequestParam @NotEmpty(message = "인증 코드가 비어있습니다.") code: String,
        @RequestParam(AuthJsonObject.previousUrl) previousUrl: String?
    ): ResponseEntity<ApiResponse<AuthTokenResponse>> {
        val logInResponse = authService.logInWithGithub(code)
        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponse.success(
                data = logInResponse,
                message = "깃허브로 로그인 되었습니다."
            )
        )
    }

    @PostMapping("/refresh")
    fun refreshToken(
        @RequestHeader("Token-Id") tokenId: Long,
        @Valid @RequestBody refreshTokenRequest: RefreshTokenRequest,
    ): ResponseEntity<ApiResponse<AuthTokenResponse>> {
        val authTokenResponse = authService.refreshingToken(
            tokenId = tokenId, 
            userId = userContextHolder.id, 
            oauthId = userContextHolder.oauthId, 
            profileImageUrl = userContextHolder.profileImageUrl,
            refreshToken = refreshTokenRequest.refreshToken
        )
        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponse.success(
                data = authTokenResponse,
                message = "토큰 정상 발급"
            )
        )
    }

}