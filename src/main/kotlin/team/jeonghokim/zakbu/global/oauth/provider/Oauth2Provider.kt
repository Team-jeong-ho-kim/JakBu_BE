package team.jeonghokim.zakbu.global.oauth.provider

enum class Oauth2Provider(val registrationId: String) {
    GOOGLE("google"),
    KAKAO("kakao");

    companion object {
        fun from(registrationId: String): Oauth2Provider? =
            entries.find { it.registrationId == registrationId }
    }
}