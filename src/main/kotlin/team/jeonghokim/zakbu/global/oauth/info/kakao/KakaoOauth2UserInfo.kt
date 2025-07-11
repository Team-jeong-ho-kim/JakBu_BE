package team.jeonghokim.zakbu.global.oauth.info.kakao

import team.jeonghokim.zakbu.global.exception.oauth.EmailNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.KakaoAccountNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.KakaoProfileNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.UserNameNotFoundException
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

class KakaoOauth2UserInfo(
    private val accessToken: String,
    private val attributes: Map<String, Any> = emptyMap()
) : Oauth2UserInfo {
    private val kakaoAccount = attributes["kakao_account"] as? Map<*, *>
        ?: throw KakaoAccountNotFoundException

    private val kakaoProfile = kakaoAccount["profile"] as? Map<*, *>
        ?: throw KakaoProfileNotFoundException

    private val email: String = kakaoAccount["email"] as? String
        ?: throw EmailNotFoundException

    private val userName: String = kakaoProfile["nickname"] as? String
        ?: throw UserNameNotFoundException

    override fun getEmail(): String {
        return email
    }

    override fun getUsername(): String {
        return userName
    }

    override fun getProvider(): Oauth2Provider {
        return Oauth2Provider.KAKAO
    }
}
