package team.jeonghokim.zakbu.domain.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import team.jeonghokim.zakbu.global.entity.BaseUUIDEntity
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider

@Entity(name = "tbl_user")
class User(
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private val email: String,

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(10)")
    private val userName: String,

    @Column(columnDefinition = "VARCHAR(20)")
    private val promise: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(6)")
    private val oauthProvider: Oauth2Provider,

    @Column(nullable = false, columnDefinition = "CHAR(255)")
    private val deviceToken: String = ""
) : BaseUUIDEntity() {
    fun getEmail(): String {
        return email
    }
}
