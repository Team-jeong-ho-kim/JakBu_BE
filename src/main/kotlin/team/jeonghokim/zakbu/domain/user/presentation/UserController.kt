package team.jeonghokim.zakbu.domain.user.presentation

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.jeonghokim.zakbu.domain.user.presentation.dto.request.SetDeviceTokenRequest
import team.jeonghokim.zakbu.domain.user.presentation.dto.request.UpdateUserRequest
import team.jeonghokim.zakbu.domain.user.presentation.dto.response.UserResponse
import team.jeonghokim.zakbu.domain.user.service.GetUserService
import team.jeonghokim.zakbu.domain.user.service.SetDeviceTokenService
import team.jeonghokim.zakbu.domain.user.service.UpdateUserService

@RestController
@RequestMapping("/users")
class UserController(
    private val getUserService: GetUserService,
    private val updateUserService: UpdateUserService,
    private val setDeviceTokenService: SetDeviceTokenService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUser(): UserResponse {
        return getUserService.execute()
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUser(
        @RequestBody @Valid
        updateUserRequest: UpdateUserRequest
    ) {
        updateUserService.execute(updateUserRequest)
    }

    @PatchMapping("/token")
    fun setToken(
        @RequestBody @Valid
        request: SetDeviceTokenRequest
    ) {
        setDeviceTokenService.execute(request)
    }
}
