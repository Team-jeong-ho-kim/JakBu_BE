package team.jeonghokim.zakbu.domain.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity(name = "tbl_user")
class User(
    @Id
    @GeneratedValue
    private val id: UUID? = null,

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private val email: String,

    @Column(nullable = false, columnDefinition = "CHAR(60)")
    private val password: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private val userName: String,

    @Column(nullable = true, columnDefinition = "VARCHAR(20)")
    private val promise: String ?,

    @Column(nullable = false, columnDefinition = "VARCHAR(6)")
    private val oauthProvider: String,

    @Column(nullable = false, columnDefinition = "CHAR(255)")
    private val deviceToken: String
) {
    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }
}