package team.jeonghokim.zakbu.global.oauth.info.google

import team.jeonghokim.zakbu.global.exception.oauth.EmailNotFoundException
import team.jeonghokim.zakbu.global.exception.oauth.UserNameNotFoundException
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

class GoogleOauth2UserInfo(
    private val accessToken: String,
    private val attributes: Map<String, Any>
) : Oauth2UserInfo {
    private val email: String = attributes["email"] as? String
        ?: throw EmailNotFoundException

    private val userName: String = attributes["name"] as? String
        ?: throw UserNameNotFoundException

    private val provider: Oauth2Provider = Oauth2Provider.GOOGLE

    override fun getEmail(): String = email

    override fun getUsername(): String = userName

    override fun getProvider(): Oauth2Provider = Oauth2Provider.GOOGLE
}
