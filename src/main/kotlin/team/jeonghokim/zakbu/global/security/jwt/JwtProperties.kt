package team.jeonghokim.zakbu.global.security.jwt

class JwtProperties(
    val secretKey: String,
    val header: String,
    val prefix: String,
    val accessExp: Long,
    val refreshExp: Long
)