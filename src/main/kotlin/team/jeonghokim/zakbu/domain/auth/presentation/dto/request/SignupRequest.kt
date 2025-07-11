package team.jeonghokim.zakbu.domain.auth.presentation.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

data class SignupRequest(
    @field:NotBlank
    val email: String,

    @field:NotBlank
    @field:Size(max = 10, message = "유저 이름은 10자 이내로 입력해주세요.")
    val userName: String,

    @field:Size(max = 20, message = "다짐은 20자 이내로 입력해주세요.")
    val promise: String,

    @field:NotNull
    val oauthProvider: Oauth2Provider
)
