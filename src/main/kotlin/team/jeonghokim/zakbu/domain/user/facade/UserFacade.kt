package team.jeonghokim.zakbu.domain.user.facade

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import team.jeonghokim.zakbu.domain.user.domain.User
import team.jeonghokim.zakbu.domain.user.domain.repository.UserRepository
import team.jeonghokim.zakbu.domain.user.exception.UserNotFoundException

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getUser(): User {
        val email = SecurityContextHolder.getContext().authentication.name

        return userRepository.findByEmail(email)
            ?: throw UserNotFoundException
    }
}
