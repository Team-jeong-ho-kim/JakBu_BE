package team.jeonghokim.zakbu.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.user.facade.UserFacade
import team.jeonghokim.zakbu.domain.user.presentation.dto.request.UpdateUserRequest

@Service
class UpdateUserService(
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: UpdateUserRequest) {
        val user = userFacade.getUser()

        user.update(request.email, request.userName, request.promise)
    }
}
