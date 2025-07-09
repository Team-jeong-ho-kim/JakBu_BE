package team.jeonghokim.zakbu.global.oauth.factory

import team.jeonghokim.zakbu.global.exception.oauth.UnsupportedProviderException
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.info.google.GoogleOauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.info.kakao.KakaoOauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

object Oauth2UserInfoFactory {
    fun getOauth2UserInfo(
        registrationId: String,
        accessToken: String,
        attributes: Map<String, Any>
    ): Oauth2UserInfo {
        val provider = Oauth2Provider.from(registrationId)
            ?: throw UnsupportedProviderException

        return when (provider) {
            Oauth2Provider.GOOGLE -> GoogleOauth2UserInfo(accessToken, attributes)
            Oauth2Provider.KAKAO -> KakaoOauth2UserInfo(accessToken, attributes)
            else -> throw UnsupportedProviderException
        }
    }
}
