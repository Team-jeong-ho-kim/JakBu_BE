package team.jeonghokim.zakbu.global.oauth.info.kakao

import team.jeonghokim.zakbu.global.exception.oauth.OauthEmailNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.OauthKakaoAccountNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.OauthKakaoProfileNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.OauthUserNameNotFoundException
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

class KakaoOauth2UserInfo(
    private val accessToken: String,
    private val attributes: Map<String, Any> = emptyMap()
) : Oauth2UserInfo {
    private val kakaoAccount = attributes["kakao_account"] as? Map<*, *>
        ?: throw OauthKakaoAccountNotFoundException

    private val kakaoProfile = kakaoAccount["profile"] as? Map<*, *>
        ?: throw OauthKakaoProfileNotFoundException

    private val email: String = kakaoAccount["email"] as? String
        ?: throw OauthEmailNotFoundException

    private val userName: String = kakaoProfile["nickname"] as? String
        ?: throw OauthUserNameNotFoundException

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
