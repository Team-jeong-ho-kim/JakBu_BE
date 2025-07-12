package team.jeonghokim.zakbu.domain.auth.presentation

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.jeonghokim.zakbu.domain.auth.presentation.dto.request.SignupRequest
import team.jeonghokim.zakbu.domain.auth.presentation.dto.response.TokenResponse
import team.jeonghokim.zakbu.domain.auth.service.ReissueService
import team.jeonghokim.zakbu.domain.auth.service.SignupService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signupService: SignupService,
    private val reissueService: ReissueService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @RequestBody @Valid
        signupRequest: SignupRequest
    ) {
        signupService.execute(signupRequest)
    }

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    fun reissue(request: HttpServletRequest): TokenResponse {
        return reissueService.execute(request)
    }
}
