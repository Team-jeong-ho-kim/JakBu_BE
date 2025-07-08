package team.jeonghokim.zakbu.global.oauth.factory

import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import team.jeonghokim.zakbu.global.oauth.info.google.GoogleOauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

object Oauth2UserInfoFactory {
    fun getOauth2UserInfo(
        registrationId: String,
        accessToken: String,
        attributes: Map<String, Any>
    ): Oauth2UserInfo {
        val provider = Oauth2Provider.from(registrationId)
            ?: throw OAuth2AuthenticationException("지원하지 않는 Oauth 제공자: $registrationId")

        return when (provider) {
            Oauth2Provider.GOOGLE -> GoogleOauth2UserInfo(accessToken, attributes)
            // 카카오 추가
            else -> throw OAuth2AuthenticationException("지원하지 않는 OAuth2 제공자: $provider")
        }
    }
}
