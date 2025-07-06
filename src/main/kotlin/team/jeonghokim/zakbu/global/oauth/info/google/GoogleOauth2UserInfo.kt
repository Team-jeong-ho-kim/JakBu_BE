package team.jeonghokim.zakbu.global.oauth.info.google

import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

class GoogleOauth2UserInfo(
    private val accessToken: String,
    private val attributes: Map<String, Any>
): Oauth2UserInfo {
    private val email: String = attributes["email"] as? String
        ?: throw OAuth2AuthenticationException("Google OAuth 응답에 email이 없습니다.")

    private val userName: String = attributes["name"] as? String
        ?: throw OAuth2AuthenticationException("Google Oauth 응답에 name이 없습니다.")

    private val provider: Oauth2Provider = Oauth2Provider.GOOGLE

    override fun getEmail(): String = email

    override fun getUsername(): String = userName

    override fun getProvider(): Oauth2Provider = Oauth2Provider.GOOGLE
}