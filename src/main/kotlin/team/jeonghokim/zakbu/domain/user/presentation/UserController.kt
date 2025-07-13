package team.jeonghokim.zakbu.domain.user.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.jeonghokim.zakbu.domain.user.presentation.dto.response.UserResponse
import team.jeonghokim.zakbu.domain.user.service.GetUserService

@RestController
@RequestMapping("/users")
class UserController(
    private val getUserService: GetUserService
) {
    @GetMapping
    fun getUser(): UserResponse {
        return getUserService.execute()
    }
}
