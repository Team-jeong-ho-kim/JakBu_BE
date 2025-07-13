package team.jeonghokim.zakbu.domain.user.presentation.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateUserRequest(
    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    @field:Size(max = 10)
    val userName: String,

    @field:Size(max = 20)
    val promise: String?
)
