package team.jeonghokim.zakbu.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.user.facade.UserFacade
import team.jeonghokim.zakbu.domain.user.presentation.dto.request.SetDeviceTokenRequest

@Service
class SetDeviceTokenService(
    private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: SetDeviceTokenRequest) {
        val user = userFacade.getUser()

        user.setToken(request.token)
    }
}
