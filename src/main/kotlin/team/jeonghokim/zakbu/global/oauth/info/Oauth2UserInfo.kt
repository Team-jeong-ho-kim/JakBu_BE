package team.jeonghokim.zakbu.global.oauth.info

import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

interface Oauth2UserInfo {
    fun getEmail(): String
    fun getUsername(): String
    fun getProvider(): Oauth2Provider
}
