package team.jeonghokim.zakbu.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.user.domain.repository.UserRepository
import team.jeonghokim.zakbu.domain.user.exception.EmailAlreadyInUseException
import team.jeonghokim.zakbu.domain.user.exception.UserNameAlreadyInUseException
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

        validateEmail(user.email, request.email)

        validateUserName(user.userName, request.userName)

        user.update(request.email, request.userName, request.promise)
    }

    private fun validateEmail(xEmail: String, newEmail: String) {
        if (xEmail != newEmail && userRepository.existsUserByEmail(newEmail)) {
            throw EmailAlreadyInUseException
        }
    }

    private fun validateUserName(xUserName: String, newUserName: String) {
        if (xUserName != newUserName && userRepository.existsUserByUserName(newUserName)) {
            throw UserNameAlreadyInUseException
        }
    }
}
