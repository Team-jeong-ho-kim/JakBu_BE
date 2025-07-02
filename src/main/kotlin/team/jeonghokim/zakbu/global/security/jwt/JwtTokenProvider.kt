package team.jeonghokim.zakbu.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import team.jeonghokim.zakbu.domain.auth.domain.RefreshToken
import team.jeonghokim.zakbu.domain.auth.domain.exception.ExpiredJwtException
import team.jeonghokim.zakbu.domain.auth.domain.exception.InvalidJwtException
import team.jeonghokim.zakbu.domain.auth.domain.repository.RefreshTokenRepository
import team.jeonghokim.zakbu.global.security.auth.AuthDetailsService
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    private val secretKeySpec = SecretKeySpec(
        jwtProperties.secretKey.toByteArray(),
        SignatureAlgorithm.HS256.jcaName)

    private fun generateToken(id: UUID, type: String, exp: Long): String {
        return Jwts.builder()
            .signWith(secretKeySpec)
            .setSubject(id.toString())
            .setHeaderParam("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
    }

    fun generateAccessToken(id: UUID): String {
        return generateToken(id, "access", jwtProperties.accessExp)
    }

    fun generateRefreshToken(id: UUID): String {
        val refreshToken = generateToken(id, "refresh", jwtProperties.refreshExp)
        refreshTokenRepository.save(
            RefreshToken(id, refreshToken, jwtProperties.refreshExp)
        )
        return refreshToken
    }

    fun parseToken(bearerToken: String?): String? {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.prefix)) {
            return bearerToken.replace(jwtProperties.prefix, "").trim()
        }
        return null
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(jwtProperties.header)
        return parseToken(bearerToken)
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKeySpec)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            when (e) {
                is io.jsonwebtoken.ExpiredJwtException -> throw ExpiredJwtException
                else -> throw InvalidJwtException
            }
        }
    }

    private fun getTokenSubject(token: String): String {
        return getClaims(token).subject
    }

    fun authentication(token: String): Authentication {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}