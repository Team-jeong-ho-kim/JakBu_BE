package team.jeonghokim.zakbu.domain.auth.presentation

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.jeonghokim.zakbu.domain.auth.presentation.dto.request.SignupRequest
import team.jeonghokim.zakbu.domain.auth.service.SignupService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signupService: SignupService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @RequestBody @Valid
        signupRequest: SignupRequest
    ) {
        signupService.execute(signupRequest)
    }
}
