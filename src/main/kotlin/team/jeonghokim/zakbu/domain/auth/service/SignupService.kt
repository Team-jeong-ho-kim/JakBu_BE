package team.jeonghokim.zakbu.domain.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.auth.presentation.dto.request.SignupRequest
import team.jeonghokim.zakbu.domain.user.domain.User
import team.jeonghokim.zakbu.domain.user.domain.repository.UserRepository
import team.jeonghokim.zakbu.domain.user.exception.AlreadyUserExistsException

@Service
class SignupService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun execute(request: SignupRequest) {
        if (userRepository.existsUserByEmail(request.email)) {
            throw AlreadyUserExistsException
        }

        val user = request.run {
            User(
                email = email,
                userName = userName,
                promise = promise,
                oauthProvider = oauthProvider
            )
        }

        userRepository.save(user)
    }
}
