package team.jeonghokim.zakbu.global.security.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import team.jeonghokim.zakbu.domain.user.domain.User
import team.jeonghokim.zakbu.global.exception.oauth.EmailNotFoundException
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

class AuthDetails(
    private val user: User?,
    private val attributes: Map<String, Any> = emptyMap(),
    private val isRegistered: Boolean = false,
    private val oauth2Provider: Oauth2Provider = Oauth2Provider.UNKNOWN
) : UserDetails, OAuth2User {
    fun isRegistered() = isRegistered

    fun getOauth2Provider(): Oauth2Provider = oauth2Provider

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return emptyList()
    }

    override fun getPassword(): String {
        return ""
    }

    override fun getUsername(): String {
        return user?.email
            ?: attributes["email"] as? String
            ?: throw EmailNotFoundException
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun <A> getAttribute(name: String): A {
        @Suppress("UNCHECKED_CAST")
        return this.attributes[name] as A
    }

    override fun getAttributes(): Map<String, Any> {
        return this.attributes
    }

    override fun getName(): String {
        return user?.email
            ?: (attributes["email"] as? String)
            ?: throw EmailNotFoundException
    }
}
