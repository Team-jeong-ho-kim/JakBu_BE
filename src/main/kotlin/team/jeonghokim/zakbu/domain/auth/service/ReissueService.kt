package team.jeonghokim.zakbu.domain.auth.service

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.jeonghokim.zakbu.domain.auth.domain.RefreshToken
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
        val refreshToken = extractTokenFromRequest(request)

        val parseToken = parseToken(refreshToken)

        val redisRefreshToken = getRefreshToken(parseToken)

        val email = redisRefreshToken.email
        refreshTokenRepository.delete(redisRefreshToken)

        return createToken(email)
    }

    private fun extractTokenFromRequest(request: HttpServletRequest): String {
        return request.getHeader(jwtProperties.header)
            ?: throw InvalidJwtException
    }

    private fun parseToken(refreshToken: String): String {
        return jwtTokenProvider.parseToken(refreshToken)
            ?: throw InvalidJwtException
    }

    private fun getRefreshToken(parseToken: String) : RefreshToken {
        return refreshTokenRepository.findByRefreshToken(parseToken)
            ?: throw RefreshTokenNotFoundException
    }

    private fun createToken(email: String): TokenResponse {
        val newAccessToken = jwtTokenProvider.generateAccessToken(email)
        val newRefreshToken = jwtTokenProvider.generateRefreshToken(email)

        return TokenResponse(
            accessToken = newAccessToken,
            accessExpiresAt = LocalDateTime.now().plusSeconds(jwtProperties.accessExp),
            refreshToken = newRefreshToken,
            refreshExpiresAt = LocalDateTime.now().plusSeconds(jwtProperties.refreshExp)
        )
    }
}
