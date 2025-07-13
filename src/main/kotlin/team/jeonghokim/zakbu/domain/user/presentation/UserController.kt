package team.jeonghokim.zakbu.domain.user.presentation

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.jeonghokim.zakbu.domain.user.presentation.dto.request.SetDeviceTokenRequest
import team.jeonghokim.zakbu.domain.user.presentation.dto.response.UserResponse
import team.jeonghokim.zakbu.domain.user.service.GetUserService
import team.jeonghokim.zakbu.domain.user.service.SetDeviceTokenService

@RestController
@RequestMapping("/users")
class UserController(
    private val getUserService: GetUserService,
    private val setDeviceTokenService: SetDeviceTokenService
) {
    @GetMapping
    fun getUser(): UserResponse {
        return getUserService.execute()
    }

    @PatchMapping("/token")
    fun setToken(
        @RequestBody @Valid
        request: SetDeviceTokenRequest
    ) {
        setDeviceTokenService.execute(request)
    }
}
