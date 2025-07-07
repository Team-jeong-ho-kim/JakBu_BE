package team.jeonghokim.zakbu.global.oauth.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import team.jeonghokim.zakbu.domain.auth.presentation.dto.response.TokenResponse
import team.jeonghokim.zakbu.global.security.auth.AuthDetails
import team.jeonghokim.zakbu.global.security.jwt.JwtProperties
import team.jeonghokim.zakbu.global.security.jwt.JwtTokenProvider
import java.io.IOException
import java.time.LocalDateTime

@Component
class Oauth2SuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtProperties: JwtProperties,
    private val objectMapper: ObjectMapper
): SimpleUrlAuthenticationSuccessHandler() {
    @Throws(IOException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse, authentication: Authentication) {
        val authDetails: AuthDetails = authentication.principal as AuthDetails
        val email: String = authDetails.attributes["email"] as String

        response.contentType = APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        if (authDetails.isRegistered()) {
            val tokenResponse = TokenResponse(
                accessToken = jwtTokenProvider.generateAccessToken(email),
                accessExpiresAt = LocalDateTime.now().plusSeconds(jwtProperties.accessExp),
                refreshToken = jwtTokenProvider.generateRefreshToken(email),
                refreshExpiresAt = LocalDateTime.now().plusSeconds(jwtProperties.refreshExp)
            )

            val tokenBody = mapOf(
                "is_registered" to true,
                "access_token" to tokenResponse.accessToken,
                "access_expired_at" to tokenResponse.accessExpiresAt,
                "refresh_token" to tokenResponse.refreshToken,
                "refresh_expires_at" to tokenResponse.refreshExpiresAt
            )
            response.writer.write(objectMapper.writeValueAsString(tokenBody))
        } else {
            val responseBody = mapOf(
                "is_registered" to false,
                "oauth_provider" to authDetails.getOauth2Provider(),
                "email" to email
            )
            response.writer.write(objectMapper.writeValueAsString(responseBody))
        }
    }
}