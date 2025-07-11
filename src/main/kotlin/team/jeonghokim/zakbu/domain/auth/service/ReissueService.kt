package team.jeonghokim.zakbu.domain.auth.service

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.auth.domain.repository.RefreshTokenRepository
import team.jeonghokim.zakbu.domain.auth.exception.RefreshTokenNotFoundException
import team.jeonghokim.zakbu.domain.auth.presentation.dto.response.TokenResponse
import team.jeonghokim.zakbu.global.exception.jwt.InvalidJwtException
import team.jeonghokim.zakbu.global.security.jwt.JwtProperties
import team.jeonghokim.zakbu.global.security.jwt.JwtTokenProvider
import java.time.LocalDateTime

@Service
class ReissueService(
    private val jwtProperties: JwtProperties,
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    @Transactional
    fun execute(request: HttpServletRequest): TokenResponse {
        val refreshToken = request.getHeader(jwtProperties.header)
            ?: throw InvalidJwtException

        val parseToken = jwtTokenProvider.parseToken(refreshToken)
            ?: throw InvalidJwtException

        val redisRefreshToken = refreshTokenRepository.findByRefreshToken(parseToken)
            ?: throw RefreshTokenNotFoundException

        val accessToken = redisRefreshToken.email
        refreshTokenRepository.delete(redisRefreshToken)

        val newAccessToken = jwtTokenProvider.generateAccessToken(accessToken)
        val newRefreshToken = jwtTokenProvider.generateRefreshToken(newAccessToken)

        return TokenResponse(
            accessToken = newAccessToken,
            accessExpiresAt = LocalDateTime.now().plusSeconds(jwtProperties.accessExp),
            refreshToken = newRefreshToken,
            refreshExpiresAt = LocalDateTime.now().plusSeconds(jwtProperties.refreshExp)
        )
    }
}
