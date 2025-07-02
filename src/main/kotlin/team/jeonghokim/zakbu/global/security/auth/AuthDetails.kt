package team.jeonghokim.zakbu.global.security.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.jeonghokim.zakbu.domain.user.domain.User

class AuthDetails(
    private val user: User
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return emptyList()
    }

    override fun getPassword(): String {
        return user.getPassword()
    }

    override fun getUsername(): String {
        return user.getEmail()
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
}