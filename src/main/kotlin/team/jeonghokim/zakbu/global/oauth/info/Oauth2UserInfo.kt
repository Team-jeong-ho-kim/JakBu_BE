package team.jeonghokim.zakbu.global.oauth.info

interface Oauth2UserInfo {
    fun getEmail(): String
    fun getUsername(): String
}