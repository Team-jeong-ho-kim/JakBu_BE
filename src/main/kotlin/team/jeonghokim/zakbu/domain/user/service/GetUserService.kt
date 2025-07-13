package team.jeonghokim.zakbu.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.user.facade.UserFacade
import team.jeonghokim.zakbu.domain.user.presentation.dto.response.UserResponse

@Service
class GetUserService(
    private val userFacade: UserFacade

) {
    @Transactional(readOnly = true)
    fun execute(): UserResponse {
        val user = userFacade.getUser()

        return UserResponse(
            email = user.email,
            userName = user.userName,
            promise = user.promise
        )
    }
}
