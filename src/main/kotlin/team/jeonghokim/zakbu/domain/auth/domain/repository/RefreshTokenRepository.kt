package team.jeonghokim.zakbu.domain.auth.domain.repository

import org.springframework.data.repository.CrudRepository
import team.jeonghokim.zakbu.domain.auth.domain.RefreshToken
import java.util.UUID

interface RefreshTokenRepository : CrudRepository<RefreshToken, UUID> {
    fun findByRefreshToken(refreshToken: String): RefreshToken?
}
