package team.jeonghokim.zakbu.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.user.domain.repository.UserRepository
import team.jeonghokim.zakbu.domain.user.exception.EmailAlreadyInUseException
import team.jeonghokim.zakbu.domain.user.facade.UserFacade
import team.jeonghokim.zakbu.domain.user.presentation.dto.request.UpdateUserRequest

@Service
class UpdateUserService(
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute(request: UpdateUserRequest) {
        val user = userFacade.getUser()

        validateEmail(request.email)

        user.update(request.email, request.userName, request.promise)
    }

    private fun validateEmail(email: String) {
        if (userRepository.existsUserByEmail(email)) {
            throw EmailAlreadyInUseException
        }
    }
}
