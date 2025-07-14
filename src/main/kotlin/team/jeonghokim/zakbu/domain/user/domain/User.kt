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
    var email: String,

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(10)")
    var userName: String,

    @Column(columnDefinition = "VARCHAR(20)")
    var promise: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(6)")
    var oauthProvider: Oauth2Provider,

    @Column(nullable = false, columnDefinition = "CHAR(255)")
    var deviceToken: String = ""
) : BaseUUIDEntity() {
    fun update(
        email: String,
        userName: String,
        promise: String?
    ) {
        this.email = email
        this.userName = userName
        this.promise = promise
    }

    fun setToken(token: String) {
        this.deviceToken = token
    }
}
