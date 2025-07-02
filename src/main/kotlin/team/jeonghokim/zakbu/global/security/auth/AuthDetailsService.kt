package team.jeonghokim.zakbu.global.security.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import team.jeonghokim.zakbu.domain.user.domain.repository.UserRepository

@Service
class AuthDetailsService(
    val userRepository: UserRepository
): UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email)
            ?.let { AuthDetails(it) }
            ?: throw UsernameNotFoundException(email)
    }
}