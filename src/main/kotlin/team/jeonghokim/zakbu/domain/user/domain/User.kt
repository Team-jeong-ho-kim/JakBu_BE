package team.jeonghokim.zakbu.domain.user.domain

import jakarta.persistence.*
import team.jeonghokim.zakbu.global.oauth.provider.Oauth2Provider
import java.util.*

@Entity(name = "tbl_user")
class User(
    @Id
    @GeneratedValue
    private val id: UUID? = null,

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
) {
    fun getEmail(): String {
        return email
    }
}
