package team.jeonghokim.zakbu.domain.user.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.jeonghokim.zakbu.domain.user.domain.User
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): User?
}
