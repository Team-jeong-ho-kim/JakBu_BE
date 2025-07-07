package team.jeonghokim.zakbu.domain.auth.service

import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import team.jeonghokim.zakbu.domain.user.domain.User
import team.jeonghokim.zakbu.domain.user.domain.repository.UserRepository
import team.jeonghokim.zakbu.global.oauth.factory.Oauth2UserInfoFactory
import team.jeonghokim.zakbu.global.oauth.info.Oauth2UserInfo
import team.jeonghokim.zakbu.global.security.auth.AuthDetails

@Service
class CustomOauth2UserService(
    private val userRepository: UserRepository
): DefaultOAuth2UserService() {
    @Transactional
    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oauth2User: OAuth2User = super.loadUser(userRequest)

        try {
            return process(userRequest, oauth2User)
        } catch (e: AuthenticationException) {
            throw e
        } catch (e: Exception) {
            throw InternalAuthenticationServiceException(e.message, e.cause)
        }
    }

    private fun process(request: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2User {
        val accessToken: String = request.accessToken.tokenValue
        val attributes: Map<String, Any> = oAuth2User.attributes

        val oauth2UserInfo = Oauth2UserInfoFactory.getOauth2UserInfo(
            registrationId = request.clientRegistration.registrationId,
            accessToken = accessToken,
            attributes = attributes
        )

        if (!StringUtils.hasText(oauth2UserInfo.getEmail())) {
            throw OAuth2AuthenticationException("이메일을 찾을 수 없습니다.")
        }

        val user: User? = userRepository.findByEmail(oauth2UserInfo.getEmail())

        return AuthDetails(user, oAuth2User.attributes, isRegistered(oauth2UserInfo))
    }

    private fun isRegistered(oauth2UserInfo: Oauth2UserInfo): Boolean {
        return userRepository.existsUsersByEmail(oauth2UserInfo.getEmail())
    }
}