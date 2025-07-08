package team.jeonghokim.zakbu.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.jwt")
class JwtProperties(
    val secretKey: String,
    val header: String,
    val prefix: String,
    val accessExp: Long,
    val refreshExp: Long
)
