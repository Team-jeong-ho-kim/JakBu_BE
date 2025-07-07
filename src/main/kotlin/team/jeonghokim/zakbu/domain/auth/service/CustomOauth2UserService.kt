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
import team.jeonghokim.zakbu.global.error.ErrorMessage
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
        val oauth2UserInfo = createUserInfo(request, oAuth2User)

        validateEmail(oauth2UserInfo)

        val user = findUser(oauth2UserInfo)

        return AuthDetails(
            user,
            oAuth2User.attributes,
            isRegistered(oauth2UserInfo),
            oauth2UserInfo.getProvider()
        )
    }

    private fun createUserInfo(request: OAuth2UserRequest, oauth2User: OAuth2User): Oauth2UserInfo {
        return Oauth2UserInfoFactory.getOauth2UserInfo(
            registrationId = request.clientRegistration.registrationId,
            accessToken = request.accessToken.tokenValue,
            attributes = oauth2User.attributes
        )
    }

    private fun validateEmail(oauth2UserInfo: Oauth2UserInfo) {
        if (!StringUtils.hasText(oauth2UserInfo.getEmail())) {
            throw OAuth2AuthenticationException(ErrorMessage.EMAIL_NOT_FOUND_MESSAGE)
        }
    }

    private fun findUser(oauth2UserInfo: Oauth2UserInfo): User? {
        return userRepository.findByEmail(oauth2UserInfo.getEmail())
    }

    private fun isRegistered(oauth2UserInfo: Oauth2UserInfo): Boolean {
        return userRepository.existsUsersByEmail(oauth2UserInfo.getEmail())
    }
}
