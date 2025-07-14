package team.jeonghokim.zakbu.domain.user.presentation.dto.request

import jakarta.validation.constraints.NotBlank

data class SetDeviceTokenRequest(
    @field:NotBlank
    val token: String
)
